package com.codingwithmitch.mviexample.repository

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.api.RetrofitBuilder
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.ui.main.state.MainViewState
import com.codingwithmitch.mviexample.util.DataState
import com.codingwithmitch.mviexample.util.GenericApiResponse
import com.codingwithmitch.mviexample.util.GenericApiResponse.ApiSuccessResponse


object Repository {
    fun getBlogPosts(): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<List<BlogPost>, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<List<BlogPost>>) {
                result.value = DataState.data(data = MainViewState(blogPosts = response.body))
            }

            override fun createCall(): LiveData<GenericApiResponse<List<BlogPost>>> {
                return RetrofitBuilder.apiService.getBlogPosts()
            }
        }.asLiveData()
    }

    fun getUser(id: String): LiveData<DataState<MainViewState>> {
        return object: NetworkBoundResource<User, MainViewState>() {
            override fun handleApiSuccessResponse(response: ApiSuccessResponse<User>) {
                result.value = DataState.data(data = MainViewState(user = response.body))
            }

            override fun createCall(): LiveData<GenericApiResponse<User>> {
                return RetrofitBuilder.apiService.getUser(id)
            }
        }.asLiveData()
    }
}