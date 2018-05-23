package com.ryadav3.mvvmsample.service.repository

import com.ryadav3.mvvmsample.service.model.Project
import android.arch.lifecycle.MutableLiveData
import android.arch.lifecycle.LiveData
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.Retrofit



class ProjectRepository {
    private val HTTPS_API_GITHUB_URL = "https://api.github.com/"
    private var gitHubService: GitHubService?=null

    init {
        val retrofit = Retrofit.Builder()
                .baseUrl(HTTPS_API_GITHUB_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        gitHubService = retrofit.create(GitHubService::class.java)
    }

    companion object {
        private var projectRepository: ProjectRepository? = null
        @Synchronized
        @JvmStatic
        fun getInstance(): ProjectRepository {
            if (projectRepository == null) {
                projectRepository = ProjectRepository()
            }
            return projectRepository!!
        }
    }


    fun getProjectList(userId: String): LiveData<List<Project>> {
        val data = MutableLiveData<List<Project>>()

        gitHubService?.getProjectList(userId)?.enqueue(object : Callback<List<Project>> {
            override fun onResponse(call: Call<List<Project>>, response: Response<List<Project>>) {
                data.value=response.body()
            }

            override fun onFailure(call: Call<List<Project>>, t: Throwable) {
                // TODO better error handling in part #2 ...
                data.value=null
            }
        })

        return data
    }

    fun getProjectDetails(userID: String, projectName: String): LiveData<Project> {
        val data = MutableLiveData<Project>()

        gitHubService?.getProjectDetails(userID, projectName)?.enqueue(object : Callback<Project> {
            override fun onResponse(call: Call<Project>, response: Response<Project>) {
                simulateDelay()
                data.value=response.body()
            }

            override fun onFailure(call: Call<Project>, t: Throwable) {
                // TODO better error handling in part #2 ...
                data.value=null
            }
        })

        return data
    }

    private fun simulateDelay() {
        try {
            Thread.sleep(10)
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }

    }
}