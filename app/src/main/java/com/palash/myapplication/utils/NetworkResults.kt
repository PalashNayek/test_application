package com.palash.myapplication.utils

sealed class NetworkResults<T>(val data: T? = null, val error_smg: String? = null) {
    class Loading<T> : NetworkResults<T>()
    class Success<T>(data: T?) : NetworkResults<T>(data)
    class Error<T>(error_smg: String?) : NetworkResults<T>(null, error_smg)
}
