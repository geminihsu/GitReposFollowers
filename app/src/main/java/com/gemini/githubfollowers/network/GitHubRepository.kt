package com.gemini.githubfollowers.network

import androidx.lifecycle.MutableLiveData
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.model.UserInfo
import kotlinx.coroutines.withContext
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.Job
import retrofit2.HttpException

class GitHubRepository {

    private var followers = mutableListOf<Follower>()
    private var mutableLiveData = MutableLiveData<List<Follower>>()

    private var userInfo = MutableLiveData<UserInfo>()
    val completableJob = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.IO + completableJob)

    var thisApiCorService  = GithubFactory.makeRetrofitService()

    fun getFollowers(username : String): MutableLiveData<List<Follower>> {
        coroutineScope.launch {
            val request = thisApiCorService.getFollowers(username)
            withContext(Dispatchers.Main) {
                try {
                    if (request.isSuccessful) {
                        request.body()?.let {
                            followers = it as MutableList<Follower>
                            mutableLiveData.value=followers;
                        }
                    }

                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return mutableLiveData;
    }

    fun getUserInfo(username : String): MutableLiveData<UserInfo> {
        coroutineScope.launch {
            val request = thisApiCorService.getUserInfo(username)
            withContext(Dispatchers.Main) {
                try {
                    if (request.isSuccessful) {
                        request.body()?.let {
                            userInfo.value= it as UserInfo
                        }
                    }

                } catch (e: HttpException) {
                    // Log exception //

                } catch (e: Throwable) {
                    // Log error //)
                }
            }
        }
        return userInfo;
    }
}