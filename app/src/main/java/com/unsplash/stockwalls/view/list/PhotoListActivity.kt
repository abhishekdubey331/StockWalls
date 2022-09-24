package com.unsplash.stockwalls.view.list

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.core.app.ActivityOptionsCompat
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.adapter.PhotoItemClicked
import com.unsplash.stockwalls.adapter.PhotoListAdapter
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.databinding.ActivityPhotoListBinding
import com.unsplash.stockwalls.utils.gone
import com.unsplash.stockwalls.utils.launchAndRepeatWithViewLifecycle
import com.unsplash.stockwalls.utils.openActivity
import com.unsplash.stockwalls.utils.visible
import com.unsplash.stockwalls.view.detail.FullPhotoActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoListActivity : AppCompatActivity(), PhotoItemClicked {

    companion object {
        private const val VIEW_TYPE_ITEM = 0
        private const val VIEW_TYPE_LOADING = 1
        private const val SPAN_SIZE = 2
    }

    private lateinit var binding: ActivityPhotoListBinding
    private val photoListViewModel: PhotoListViewModel by viewModels()
    private lateinit var mLayoutManager: RecyclerView.LayoutManager
    private val mAdapter by lazy { PhotoListAdapter(this) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPhotoListBinding.inflate(layoutInflater)
        setContentView(binding.root)
        launchAndRepeatWithViewLifecycle {
            photoListViewModel.photoFetchEvent.collect { screenState ->
                handleLoadingState(screenState.loading)
                updateRecyclerView(screenState.photosList)
            }
        }
    }

    private fun updateRecyclerView(photosList: List<UnsplashPhotoItem>) {
        setLayoutManager()
        mAdapter.submitList(photosList)
    }

    private fun handleLoadingState(isLoading: Boolean) {
        binding.progressBar.run {
            if (isLoading) visible() else gone()
        }
    }

    private fun setLayoutManager() {
        mLayoutManager = GridLayoutManager(this, SPAN_SIZE)
        binding.recyclerView.layoutManager = mLayoutManager
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = mAdapter
        (mLayoutManager as? GridLayoutManager)?.let {
            it.spanSizeLookup =
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
    }

    override fun onItemClicked(
        item: UnsplashPhotoItem,
        appCompatImageView: AppCompatImageView
    ) {
        val options = ActivityOptionsCompat.makeSceneTransitionAnimation(
            this,
            appCompatImageView,
            getString(R.string.app_name)
        )
        openActivity(FullPhotoActivity::class.java, options) {
            putParcelable(FullPhotoActivity.PHOTO_KEY, item)
        }
    }
}
