package com.unsplash.stockwalls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.data.model.UnsplashPhotoItem
import com.unsplash.stockwalls.utils.loadImageWithPlaceholder

class PhotoListAdapter(private val listener: PhotoItemClicked) :
    ListAdapter<UnsplashPhotoItem, PhotoListAdapter.PhotoViewHolder>(object :
        DiffUtil.ItemCallback<UnsplashPhotoItem>() {
        override fun areItemsTheSame(
            oldItem: UnsplashPhotoItem,
            newItem: UnsplashPhotoItem
        ) = oldItem.id == newItem.id

        override fun areContentsTheSame(
            oldItem: UnsplashPhotoItem,
            newItem: UnsplashPhotoItem
        ) = oldItem.id == newItem.id
    }) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        return PhotoViewHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val photo = getItem(position)
        holder.photoImageView.run {
            loadImageWithPlaceholder(photo?.urls?.small ?: "")
            setOnClickListener {
                listener.onItemClicked(
                    getItem(position),
                    holder.photoImageView
                )
            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: AppCompatImageView = itemView.findViewById(R.id.photoImv)
    }
}

interface PhotoItemClicked {
    fun onItemClicked(item: UnsplashPhotoItem, appCompatImageView: AppCompatImageView)
}