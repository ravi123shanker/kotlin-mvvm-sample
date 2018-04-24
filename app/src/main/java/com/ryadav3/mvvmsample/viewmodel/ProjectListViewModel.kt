package com.ryadav3.mvvmsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import android.arch.lifecycle.LiveData
import com.ryadav3.mvvmsample.service.model.Project
import com.ryadav3.mvvmsample.service.repository.ProjectRepository


class ProjectListViewModel(application: Application): AndroidViewModel(application) {
    private val projectListObservable= ProjectRepository.getInstance().getProjectList("Google")

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    fun getProjectListObservable(): LiveData<List<Project>> {
        return projectListObservable
    }
}