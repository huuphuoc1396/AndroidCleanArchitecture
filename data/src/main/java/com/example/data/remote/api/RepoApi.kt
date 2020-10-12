package com.example.data.remote.api

import com.example.data.remote.response.RepoListResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RepoApi {

    @GET("search/repositories")
    suspend fun searchRepos(@Query("q") query: String): RepoListResponse
}