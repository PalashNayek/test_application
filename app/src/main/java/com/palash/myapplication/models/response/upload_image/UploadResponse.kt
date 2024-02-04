package com.palash.myapplication.models.response.upload_image

data class UploadResponse(
    val success: Boolean,
    val message: String,
    val uploadedImageUrl: String // Assuming the server sends back the uploaded image URL
)