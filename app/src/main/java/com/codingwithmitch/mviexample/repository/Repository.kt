package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.codingwithmitch.mviexample.api.RetrofitBuilder
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.DataState
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiEmptyResponse
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiErrorResponse
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiSuccessResponse


object Repository {
    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    value = when (apiResponse) {
                        is ApiSuccessResponse -> {
                            DataState.data(data = MainViewState(blogPosts = apiResponse.body))
                        }
                        is ApiErrorResponse -> {
                            DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            DataState.error(message = "HTTP 204. Returned nothing.")
                        }
                    }
                }
            }
        }
    }

    fun getUser(id: String): LiveData<DataState<MainViewState>> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getUser(id)) { apiResponse ->
            object : LiveData<DataState<MainViewState>>() {
                override fun onActive() {
                    super.onActive()
                    value = when (apiResponse) {
                        is ApiSuccessResponse -> {
                            DataState.data(data = MainViewState(user = apiResponse.body))
                        }
                        is ApiErrorResponse -> {
                            DataState.error(message = apiResponse.errorMessage)
                        }
                        is ApiEmptyResponse -> {
                            DataState.error(message = "HTTP 204. Returned nothing.")
                        }
                    }
                }
            }
        }
    }
}