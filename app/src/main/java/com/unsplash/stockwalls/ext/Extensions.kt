package com.unsplash.stockwalls.ext

import android.graphics.Color
import android.view.WindowManager
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.unsplash.stockwalls.ui.detail.fragment.FullPhotoFragment

fun FragmentManager.replaceFragment(
    containerId: Int,
    fragment: Fragment,
    addToBackStack: Boolean = true,
    tag: String? = null
) {
    beginTransaction().apply {
        replace(containerId, fragment)
        if (addToBackStack) {
            addToBackStack(tag)
        }
        commit()
    }
}

fun FullPhotoFragment.transparentStatusBar() {
    this.activity?.window?.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
    this.activity?.window?.statusBarColor = Color.TRANSPARENT
}
