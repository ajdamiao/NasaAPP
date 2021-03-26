package com.example.nasaapp.model


data class NasaResponseItem(
    val caption: String,
    val centroid_coordinates: CentroidCoordinates,
    val date: String,
    val identifier: String,
    val image: String,
    val version: String

    )