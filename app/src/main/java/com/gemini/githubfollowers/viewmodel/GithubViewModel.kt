package com.gemini.githubfollowers.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.model.UserInfo
import com.gemini.githubfollowers.network.GitHubRepository


class GithubViewModel() : ViewModel() {

    private var gitRepository= GitHubRepository()

    var followers = MutableLiveData<List<Follower>>()
    var user = MutableLiveData<UserInfo>()


    fun fetchFollowers(username : String) {
        followers = gitRepository.getFollowers(username)
    }

    fun fetchUserInfo(username : String) {
        user = gitRepository.getUserInfo(username)
    }
    override fun onCleared() {
        super.onCleared()
        gitRepository.completableJob.cancel()
    }
}