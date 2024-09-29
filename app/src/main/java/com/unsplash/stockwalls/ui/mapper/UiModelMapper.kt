package com.unsplash.stockwalls.ui.mapper

import android.os.Parcelable
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import kotlinx.parcelize.Parcelize
import kotlinx.serialization.Serializable

fun UnsplashPhotoItemDto.toPhotoUIModel(): PhotoUIModel {
    return PhotoUIModel(
        id = this.id.orEmpty(),
        description = alt_description,
        thumbnailImageUrl = urls.thumb.orEmpty(),
        fullImageUrl = urls.full.orEmpty(),
        regularImageUrl = urls.regular.orEmpty(),
        smallImageUrl = urls.small.orEmpty(),
        likeCount = likes,
        photographerName = user.name ?: "Unknown",
        photographerImageUrl = user.profile_image.small.orEmpty()
    )
}

@Serializable
data class PhotoUIModel(
    val id: String,
    val description: String?,
    val fullImageUrl: String,
    val regularImageUrl: String,
    val smallImageUrl: String,
    val thumbnailImageUrl: String,
    val likeCount: Int,
    val photographerName: String,
    val photographerImageUrl: String
)

@Parcelize
data class UserUIModel(
    val name: String?,
    val profileImageUrl: String
) : Parcelable
