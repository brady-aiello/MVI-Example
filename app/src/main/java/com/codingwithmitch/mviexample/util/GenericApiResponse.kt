package com.codingwithmitch.mviexample.util

import android.util.Log
import retrofit2.Response

sealed class GenericApiResponse<T> {
    companion object {
        private const val TAG = "GenericApiResponse"

        fun <T> create(error: Throwable): ApiErrorResponse<T> {
            return ApiErrorResponse(error.message ?: "unknown error")
        }

        fun <T> create(response: Response<T>): GenericApiResponse<T> {
            Log.d(TAG, "create: response: $response")
            Log.d(TAG, "create: raw: ${response.raw()}")
            Log.d(TAG, "create: headers: ${response.headers()}")
            Log.d(TAG, "create: message: ${response.message()}")
            if (response.isSuccessful) {
                val body = response.body()
                return if (body == null || response.code() == 204) {
                     ApiEmptyResponse()
                } else if (response.code() == 401) {
                    ApiErrorResponse("401 Unauthorized. Token may be invalid.")
                } else {
                    ApiSuccessResponse(body)
                }
            } else {
                val msg = response.errorBody()?.string()
                val errorMessage = if (msg.isNullOrEmpty()) {
                    response.message()
                } else {
                    msg
                }
                return ApiErrorResponse(errorMessage ?: "unknown error")
            }
        }
    }
    class ApiEmptyResponse<T> : GenericApiResponse<T>()
    data class ApiSuccessResponse<T>(val body: T) : GenericApiResponse<T>()
    data class ApiErrorResponse<T>(val errorMessage: String) : GenericApiResponse<T>()
}