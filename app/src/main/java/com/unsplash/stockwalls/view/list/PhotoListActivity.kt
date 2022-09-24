package com.unsplash.stockwalls.view.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.adapter.PhotoAdapter
import com.unsplash.stockwalls.adapter.PhotoItemClicked
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.databinding.ActivityPhotoListBinding
import com.unsplash.stockwalls.utils.OnLoadMoreListener
import com.unsplash.stockwalls.utils.RecyclerViewLoadMoreScroll
import com.unsplash.stockwalls.utils.openActivity
import com.unsplash.stockwalls.view.detail.FullPhotoActivity
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect


const val VIEW_TYPE_ITEM = 0
const val VIEW_TYPE_LOADING = 1
const val SPAN_SIZE = 2

@AndroidEntryPoint
class PhotoListActivity : AppCompatActivity(), PhotoItemClicked {

    private lateinit var binding: ActivityPhotoListBinding
    private val photoListViewModel: PhotoListViewModel by viewModels()
    private lateinit var mAdapter: PhotoAdapter
    private lateinit var scrollListener: RecyclerViewLoadMoreScroll
    private lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAdapter = PhotoAdapter(this)
        setRVLayoutManager()
        lifecycleScope.launchWhenStarted {
            photoListViewModel.photoFetchEvent.collect { event ->
                when (event) {
                    is PhotoFetchEvent.Success -> {
                        event.unsplashPhoto?.toMutableList()?.let {
                            mAdapter.removeLoadingView()
                            mAdapter.submitData(it)
                            binding.recyclerView.post {
                                mAdapter.notifyAdapter(photoListViewModel.currentPage, it.size)
                                binding.progressBar.isVisible = false
                                setRVScrollListener()
                            }
                        }
                    }
                    is PhotoFetchEvent.Failure -> {
                        binding.progressBar.isVisible = false
                    }
                    is PhotoFetchEvent.Loading -> {
                        if (photoListViewModel.currentPage < 2) {
                            binding.progressBar.isVisible = true
                        }
                    }
                    else -> Unit
                }
            }
        }
    }

    private fun setRVLayoutManager() {
        mLayoutManager = GridLayoutManager(this, SPAN_SIZE)
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mAdapter
        (mLayoutManager as GridLayoutManager).spanSizeLookup =
            object : GridLayoutManager.SpanSizeLookup() {
                override fun getSpanSize(position: Int): Int {
                    return when (mAdapter.getItemViewType(position)) {
                        VIEW_TYPE_ITEM -> 1
                        VIEW_TYPE_LOADING -> 2
                        else -> -1
                    }
                }
            }
    }

    private fun setRVScrollListener() {
        scrollListener = RecyclerViewLoadMoreScroll(mLayoutManager as GridLayoutManager)
        scrollListener.setOnLoadMoreListener(object : OnLoadMoreListener {
            override fun onLoadMore() {
                binding.recyclerView.post { mAdapter.addLoadingView() }
                photoListViewModel.fetchPhotosByPage(photoListViewModel.currentPage)
            }
        })
        binding.recyclerView.addOnScrollListener(scrollListener)
    }

    override fun onItemClicked(item: UnsplashPhotoItem?, imageView: AppCompatImageView) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            imageView,
            getString(R.string.app_name)
        )
        openActivity(FullPhotoActivity::class.java, options) {
            putParcelable(FullPhotoActivity.PHOTO_KEY, item)
        }
    }
}
