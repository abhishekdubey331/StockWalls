package com.unsplash.stockwalls.view.detail

import android.os.Bundle
import android.os.Parcelable
import androidx.appcompat.app.AppCompatActivity
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.databinding.ActivityFullPhotoBinding
import com.unsplash.stockwalls.utils.loadFullImage

class FullPhotoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFullPhotoBinding

    companion object {
        const val PHOTO_KEY = "photoKey"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFullPhotoBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val data = intent.extras
        val unsplashPhoto = data?.getParcelable<Parcelable>(PHOTO_KEY) as UnsplashPhotoItem?
        binding.fullPhotoImv.loadFullImage(
            unsplashPhoto?.urls?.full ?: "",
            unsplashPhoto?.urls?.small ?: ""
        )
    }
}