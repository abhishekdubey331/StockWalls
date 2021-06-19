package com.unsplash.stockwalls.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.unsplash.stockwalls.R
import com.unsplash.stockwalls.data.UnsplashPhotoItem
import com.unsplash.stockwalls.utils.loadImageWithPlaceholder

const val PAGE_SIZE = 10
const val VIEW_TYPE_ITEM = 0
const val VIEW_TYPE_LOADING = 1

class PhotoAdapter(private val listener: PhotoItemClicked) :
    ListAdapter<UnsplashPhotoItem, RecyclerView.ViewHolder>(DIFF_CALLBACK) {

    private val photosList: MutableList<UnsplashPhotoItem?> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        when (viewType) {
            VIEW_TYPE_ITEM -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
                val viewHolder = PhotoViewHolder(view)
                view.setOnClickListener {
                    listener.onItemClicked(
                        photosList[viewHolder.adapterPosition],
                        viewHolder.photoImageView
                    )
                }
                return viewHolder
            }
            VIEW_TYPE_LOADING -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.progress_loading, parent, false)
                return LoadingViewHolder(view)
            }
            else -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_photo, parent, false)
                val viewHolder = PhotoViewHolder(view)
                view.setOnClickListener {
                    listener.onItemClicked(
                        photosList[viewHolder.adapterPosition],
                        viewHolder.photoImageView
                    )
                }
                return viewHolder
            }
        }
    }

    override fun getItemCount() = photosList.size

    override fun getItemViewType(position: Int): Int {
        return if (photosList[position] == null) {
            VIEW_TYPE_LOADING
        } else {
            VIEW_TYPE_ITEM
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PhotoViewHolder -> {
                val currentItem = photosList[position]
                holder.photoImageView.loadImageWithPlaceholder(
                    currentItem?.urls?.regular ?: ""
                )
            }
        }
    }

    class PhotoViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val photoImageView: AppCompatImageView = itemView.findViewById(R.id.photoImv)
    }

    class LoadingViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

    fun submitData(paginatedData: List<UnsplashPhotoItem>) {
        photosList.addAll(paginatedData)
    }

    fun notifyAdapter(page: Int, pageSize: Int) {
        if (photosList.isEmpty())
            notifyDataSetChanged()
        else
            notifyItemRangeInserted(PAGE_SIZE * page, pageSize)
    }

    fun addLoadingView() {
        photosList.add(null)
        notifyItemInserted(photosList.size - 1)
    }

    fun removeLoadingView() {
        if (photosList.isNotEmpty()) {
            photosList.removeAt(photosList.size - 1)
            notifyItemRemoved(photosList.size)
        }
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
    fun onItemClicked(item: UnsplashPhotoItem?, appCompatImageView: AppCompatImageView)
}