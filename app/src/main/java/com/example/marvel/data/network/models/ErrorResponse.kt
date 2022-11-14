package com.example.marvel.data.network.models

import kotlinx.serialization.Serializable

@Serializable
data class ErrorResponse(
    val codeError: String,
    val messageError: String
)
