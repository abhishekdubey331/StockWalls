package com.unsplash.stockwalls.ui.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.unsplash.stockwalls.ext.transparentStatusBar
import com.unsplash.stockwalls.ui.detail.composables.FullPhotoScreen
import com.unsplash.stockwalls.ui.mapper.PhotoUIModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class FullPhotoFragment : Fragment() {

    private lateinit var photoUIModel: PhotoUIModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        transparentStatusBar()
        photoUIModel = requireNotNull(arguments?.getParcelable(PHOTO_KEY))
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val composeView = ComposeView(requireContext()).apply {
            setContent {
                FullPhotoScreen(photoItem = photoUIModel, onBackClick = {
                    parentFragmentManager.popBackStack()
                })
            }
        }
        return composeView
    }

    companion object {
        const val PHOTO_KEY = "photoKey"
        const val TAG = "FullPhotoFragment"

        fun newInstance(photoItem: PhotoUIModel): FullPhotoFragment {
            val fragment = FullPhotoFragment()
            val args = Bundle()
            args.putParcelable(PHOTO_KEY, photoItem)
            fragment.arguments = args
            return fragment
        }
    }
}
