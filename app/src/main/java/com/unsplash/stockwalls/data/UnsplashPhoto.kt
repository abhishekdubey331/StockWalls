package com.unsplash.stockwalls.data

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

class UnsplashPhoto : ArrayList<UnsplashPhotoItem>()

@Parcelize
data class UnsplashPhotoItem(
    val alt_description: String?,
    val blur_hash: String?,
    val color: String?,
    val created_at: String?,
    val description: String?,
    val height: Int,
    val id: String?,
    val liked_by_user: Boolean,
    val likes: Int,
    val links: Links,
    val promoted_at: String?,
    val updated_at: String?,
    val urls: Urls,
    val user: User,
    val width: Int
) : Parcelable

@Parcelize
data class Urls(
    val full: String?,
    val raw: String?,
    val regular: String?,
    val small: String?,
    val thumb: String?
) : Parcelable

@Parcelize
data class User(
    val accepted_tos: Boolean,
    val bio: String?,
    val first_name: String?,
    val for_hire: Boolean,
    val id: String?,
    val instagram_username: String?,
    val last_name: String?,
    val links: Links,
    val location: String?,
    val name: String?,
    val portfolio_url: String?,
    val profile_image: ProfileImage,
    val total_collections: Int,
    val total_likes: Int,
    val total_photos: Int,
    val twitter_username: String?,
    val updated_at: String?,
    val username: String?
) : Parcelable

@Parcelize
data class Links(
    val followers: String?,
    val following: String?,
    val html: String?,
    val likes: String?,
    val photos: String?,
    val portfolio: String?,
    val self: String?,
    val download: String?,
    val download_location: String?
) : Parcelable

@Parcelize
data class ProfileImage(
    val large: String?,
    val medium: String?,
    val small: String?
) : Parcelable