package com.palash.myapplication.models.response.record_update

data class UpdateRecordResponse(
    val `data`: Data,
    val message: String,
    val status: String
)