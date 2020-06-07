package com.codingwithmitch.mviexample.util

import androidx.lifecycle.LiveData
import com.codingwithmitch.mviexample.model.BlogPost
import com.codingwithmitch.mviexample.model.User
import com.codingwithmitch.mviexample.ui.main.state.MainViewState

fun getTestLiveDataBlogs(): LiveData<MainViewState> {
    return object: LiveData<MainViewState>(){
        override fun onActive() {
            super.onActive()
            val blogList: ArrayList<BlogPost> = ArrayList()
            blogList.add(
                BlogPost(
                    primaryKey = 0,
                    title = "Vancouver PNE 2019",
                    body = "Here is Jess and I at the Vancouver PNE. We ate a lot of food.",
                    image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image8.jpg"
                )
            )
            blogList.add(
                BlogPost (
                    primaryKey = 1,
                    title = "Ready for a Walk",
                    body = "Here I am at the park with my dogs Kiba and Maizy. Maizy is the smaller one and Kiba is the larger one.",
                    image = "https://cdn.open-api.xyz/open-api-static/static-blog-images/image2.jpg"
                )
            )
            value = MainViewState(
                blogPosts = blogList
            )
        }
    }
}

fun getTestLiveDataUser(): LiveData<MainViewState> {
    return object: LiveData<MainViewState>(){
        override fun onActive() {
            super.onActive()
            val user = User(
                email = "mitch@tabian.ca",
                userName = "mitch",
                image = "https://cdn.open-api.xyz/open-api-static/static-random-images/logo_1080_1080.png"
            )
            value = MainViewState(
                user = user
            )
        }
    }
}