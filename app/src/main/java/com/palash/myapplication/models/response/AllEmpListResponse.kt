package com.palash.myapplication.models.response

data class AllEmpListResponse(
    val `data`: List<Data>,
    val message: String,
    val status: String
)