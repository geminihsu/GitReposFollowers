package com.gemini.githubfollowers.network
import com.gemini.githubfollowers.model.Follower
import com.gemini.githubfollowers.model.UserInfo
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface GithubApi {
    @GET("/users/{username}/followers")
    suspend fun getFollowers(@Path("username") id:String): Response<List<Follower>>

    @GET("/users/{username}")
    suspend fun getUserInfo(@Path("username") id:String): Response<UserInfo>

}