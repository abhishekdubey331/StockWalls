package com.unsplash.stockwalls.ui.list.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.ext.replaceFragment
import com.unsplash.stockwalls.ui.detail.fragment.FullPhotoFragment
import com.unsplash.stockwalls.ui.list.composables.PhotoListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return ComposeView(requireContext()).apply {
            setContent {
                PhotoListScreen { unsplashPhotoItem ->
                    parentFragmentManager.replaceFragment(
                        R.id.fragment_container,
                        FullPhotoFragment.newInstance(unsplashPhotoItem),
                        addToBackStack = true,
                        tag = FullPhotoFragment.TAG
                    )
                }
            }
        }
    }
}
