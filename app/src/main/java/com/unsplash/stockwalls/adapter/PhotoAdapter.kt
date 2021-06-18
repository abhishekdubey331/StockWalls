package com.unsplash.stockwalls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.data.UnsplashPhotoItem

class PhotoAdapter(private val listener: PhotoItemClicked) :
    ListAdapter<UnsplashPhotoItem, PhotoAdapter.PhotoViewHolder>(DIFF_CALLBACK) {

    private val items: MutableList<UnsplashPhotoItem> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
        val viewHolder = PhotoViewHolder(view)
        view.setOnClickListener {
            listener.onItemClicked(items[viewHolder.adapterPosition])
        }
        return viewHolder
    }

    override fun getItemCount() = items.size

    override fun onBindViewHolder(holder: PhotoViewHolder, position: Int) {
        val currentItem = items[position]
        holder.photoImageView.load(currentItem.urls.small)
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: AppCompatImageView = itemView.findViewById(R.id.photoImv)
    }

    fun updatePhotos(updatedNews: MutableList<UnsplashPhotoItem>) {
        items.clear()
        items.addAll(updatedNews)
        notifyDataSetChanged()
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<UnsplashPhotoItem>() {
            override fun areItemsTheSame(
                oldItem: UnsplashPhotoItem,
                newItem: UnsplashPhotoItem
            ): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: UnsplashPhotoItem,
                newItem: UnsplashPhotoItem
            ): Boolean = oldItem == newItem
        }
    }
}

interface PhotoItemClicked {
    fun onItemClicked(item: UnsplashPhotoItem)
}