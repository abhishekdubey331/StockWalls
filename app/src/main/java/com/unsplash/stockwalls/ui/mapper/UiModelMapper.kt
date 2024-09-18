package com.unsplash.stockwalls.ui.mapper

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import com.unsplash.stockwalls.data.model.UnsplashPhotoItemDto
import kotlinx.parcelize.Parcelize

fun UnsplashPhotoItemDto.toPhotoUIModel(): PhotoUIModel {
    return PhotoUIModel(
        id = this.id.orEmpty(),
        description = alt_description,
        thumbnailImageUrl = urls.thumb.orEmpty(),
        fullImageUrl = urls.full.orEmpty(),
        smallImageUrl = urls.small.orEmpty(),
        likeCount = likes,
        photographerName = user.name ?: "Unknown",
        photographerImageUrl = user.profile_image.small.orEmpty()
    )
}

@Immutable
@Parcelize
data class PhotoUIModel(
    val id: String,
    val description: String?,
    val fullImageUrl: String,
    val smallImageUrl: String,
    val thumbnailImageUrl: String,
    val likeCount: Int,
    val photographerName: String,
    val photographerImageUrl: String
) : Parcelable

@Immutable
@Parcelize
data class UserUIModel(
    val name: String?,
    val profileImageUrl: String
) : Parcelable
