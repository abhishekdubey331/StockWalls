package com.unsplash.stockwalls.ui.detail

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import com.unsplash.stockwalls.databinding.ActivityFullPhotoBinding
import com.unsplash.stockwalls.utils.loadImage
import com.unsplash.stockwalls.utils.transparentStatusBar

class FullPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullPhotoBinding

    companion object {
        const val PHOTO_KEY = "photoKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        transparentStatusBar()
        val data = intent.extras
        val unsplashPhoto = data?.getParcelable<Parcelable>(PHOTO_KEY) as UnsplashPhotoItemDto?
        binding.fullPhotoImv.loadImage(
            unsplashPhoto?.urls?.regular ?: ""
        )
        binding.backButton.setOnClickListener {
            supportFinishAfterTransition()
        }
    }
}