package com.palash.myapplication.models.response.single_record_response

data class SingleEmpRecordResponse(
    val `data`: Data,
    val message: String,
    val status: String
)