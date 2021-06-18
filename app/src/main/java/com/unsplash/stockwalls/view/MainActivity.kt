package com.unsplash.stockwalls.view

import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.unsplash.stockwalls.adapter.PhotoAdapter
import com.unsplash.stockwalls.adapter.PhotoItemClicked
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.databinding.ActivityMainBinding
import com.unsplash.stockwalls.utils.toast
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect

const val SPAN_SIZE = 2

@AndroidEntryPoint
class MainActivity : AppCompatActivity(), PhotoItemClicked {

    private lateinit var binding: ActivityMainBinding
    private val mainViewModel: MainViewModel by viewModels()
    private lateinit var mAdapter: PhotoAdapter
    private var loading = true
    private var pastVisibleItems = 0
    private var visibleItemCount: Int = 0
    private var totalItemCount: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        mAdapter = PhotoAdapter(this)
        mAdapter.setHasStableIds(true)
        val staggeredLayoutManager = StaggeredGridLayoutManager(SPAN_SIZE, SPAN_SIZE - 1)
        staggeredLayoutManager.gapStrategy = SPAN_SIZE
        binding.recyclerView.layoutManager = staggeredLayoutManager
        binding.recyclerView.adapter = mAdapter
        binding.recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                visibleItemCount = staggeredLayoutManager.childCount
                totalItemCount = staggeredLayoutManager.itemCount
                var firstVisibleItems: IntArray? = null
                firstVisibleItems =
                    staggeredLayoutManager.findFirstVisibleItemPositions(firstVisibleItems)
                if (firstVisibleItems != null && firstVisibleItems.isNotEmpty()) {
                    pastVisibleItems = firstVisibleItems.first()
                }
                Log.d("tag", "LOAD NEXT ITEM")
                if (loading) {
                    if (visibleItemCount + pastVisibleItems >= totalItemCount) {
                        loading = false
                        Log.d("tag", "LOAD NEXT ITEM")
                        mainViewModel.fetchPhotosByPage(mainViewModel.currentPage)
                    }
                }
            }
        })
        mainViewModel.fetchPhotosByPage(mainViewModel.currentPage)

        lifecycleScope.launchWhenStarted {
            mainViewModel.photoFetchEvent.collect { event ->
                when (event) {

                    is MainViewModel.PhotoFetchEvent.Success -> {
                        binding.progressBar.isVisible = false
                        event.unsplashPhoto?.toMutableList()?.let {
                            mAdapter.updatePhotos(it)
                        }
                    }

                    is MainViewModel.PhotoFetchEvent.Failure -> {
                        binding.progressBar.isVisible = false
                    }

                    is MainViewModel.PhotoFetchEvent.Loading -> {
                        binding.progressBar.isVisible = true
                    }

                    else -> Unit
                }
            }
        }
    }

    override fun onItemClicked(item: UnsplashPhotoItem) {
        item.urls.small.toast()
    }
}