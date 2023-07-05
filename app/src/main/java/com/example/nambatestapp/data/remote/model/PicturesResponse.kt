package com.example.nambatestapp.data.remote.model

data class PicturesResponse<T>(
    val total: Int,
    val totalHits: Int,
    val hits: List<T>
)