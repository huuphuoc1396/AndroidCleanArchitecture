package com.example.clean_architecture.data.remote.retrofit.api

import com.example.clean_architecture.data.remote.response.RepoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String): RepoListResponse
}