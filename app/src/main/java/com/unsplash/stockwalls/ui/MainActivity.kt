package com.unsplash.stockwalls.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.ui.list.fragment.PhotoListFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment_container, PhotoListFragment())
                .commit()
        }
    }
}
