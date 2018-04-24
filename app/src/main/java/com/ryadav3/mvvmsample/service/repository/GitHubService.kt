package com.ryadav3.mvvmsample.service.repository

import com.ryadav3.mvvmsample.service.model.Project
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface GitHubService {
    @GET("users/{user}/repos")
    fun getProjectList(@Path("user") user: String): Call<List<Project>>

    @GET("/repos/{user}/{reponame}")
    fun getProjectDetails(@Path("user") user: String, @Path("reponame") projectName: String): Call<Project>
}