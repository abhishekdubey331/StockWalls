package com.unsplash.stockwalls.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
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
        mainViewModel.fetchUsers()

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