package com.example.nambatestapp.data.remote.model

import com.example.nambatestapp.domain.model.PicturesModel
import com.google.gson.annotations.SerializedName

data class PicturesDto(
    val id: Int,
    val type: String,
    val tags: String,
    val previewURL: String,
    val previewWidth: Int,
    val previewHeight: Int,
    @SerializedName("webformatURL")
    val webFormatURL: String,
    @SerializedName("webformatWidth")
    val webFormatWidth: Int,
    @SerializedName("webformatHeight")
    val webFormatHeight: Int,
    val largeImageURL: String,
    val imageWidth: Int,
    val imageHeight: Int,
    val imageSize: Int,
    val views: Int,
    val downloads: Int,
    val collections: Int,
    val likes: Int,
    val comments: Int,
    @SerializedName("user_id")
    val userId: Int,
    val user: String,
    val userImageURL: String,
)

fun PicturesDto.toModel() = PicturesModel(
    id = id,
    type = type,
    tags = tags,
    previewURL = previewURL,
    previewWidth = previewWidth,
    previewHeight = previewHeight,
    webFormatURL = webFormatURL,
    webFormatWidth = webFormatWidth,
    webFormatHeight = webFormatHeight,
    largeImageURL = largeImageURL,
    imageWidth = imageWidth,
    imageHeight = imageHeight,
    imageSize = imageSize,
    views = views,
    downloads = downloads,
    collections = collections,
    likes = likes,
    comments = comments,
    userId = userId,
    user = user,
    userImageURL = userImageURL
)

