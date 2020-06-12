package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.codingwithmitch.mviexample.api.RetrofitBuilder
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiEmptyResponse
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiErrorResponse
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiSuccessResponse


object Repository {
    fun getBlogPosts(): LiveData<MainViewState> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getBlogPosts()) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    value = when (apiResponse) {
                        is ApiSuccessResponse -> {
                            MainViewState(blogPosts = apiResponse.body)
                        }
                        is ApiErrorResponse -> {
                            MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            MainViewState()
                        }
                    }
                }
            }
        }
    }

    fun getUser(id: String): LiveData<MainViewState> {
        return Transformations.switchMap(RetrofitBuilder.apiService.getUser(id)) { apiResponse ->
            object : LiveData<MainViewState>() {
                override fun onActive() {
                    super.onActive()
                    value = when (apiResponse) {
                        is ApiSuccessResponse -> {
                            MainViewState(user = apiResponse.body)
                        }
                        is ApiErrorResponse -> {
                            MainViewState()
                        }
                        is ApiEmptyResponse -> {
                            MainViewState()
                        }
                    }
                }
            }
        }
    }
}