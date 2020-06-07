package com.codingwithmitch.mviexample.ui.main.state

sealed class MainStateEvent{
    object GetBlogPostsEvent: MainStateEvent()
    class GetUserEvent(val userId: String): MainStateEvent()
    object None: MainStateEvent()
}