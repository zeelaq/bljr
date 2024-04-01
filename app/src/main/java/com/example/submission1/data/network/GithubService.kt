package com.example.submission1.data.network

import com.example.submission1.data.responsemodel.ResponseDetailUser
import com.example.submission1.data.responsemodel.ResponseUserGithub
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.QueryMap

interface GithubService {

    @JvmSuppressWildcards
    @GET("users")
    suspend fun getUserGithub(): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("users/{username}")
    suspend fun getDetailUserGithub(@Path("username") username: String): ResponseDetailUser

    @JvmSuppressWildcards
    @GET("users/{username}/followers")
    suspend fun getFollowersUserGithub(@Path("username") username: String): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("users/{username}/following")
    suspend fun getFollowingUserGithub(@Path("username") username: String): MutableList<ResponseUserGithub.Item>

    @JvmSuppressWildcards
    @GET("search/users")
    suspend fun searchUserGithub(@QueryMap params: Map<String, Any>): ResponseUserGithub
}