package com.ryadav3.mvvmsample.viewmodel

import android.app.Application
import android.arch.lifecycle.AndroidViewModel
import com.ryadav3.mvvmsample.service.model.Project
import android.arch.lifecycle.LiveData
import android.databinding.ObservableField
import com.ryadav3.mvvmsample.service.repository.ProjectRepository
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider

class ProjectViewModel(application: Application, projectID: String): AndroidViewModel(application) {
    private val projectObservable: LiveData<Project> = ProjectRepository.getInstance().getProjectDetails("Google", projectID)
    var project: ObservableField<Project> = ObservableField()
    fun getObservableProject(): LiveData<Project> {
        return projectObservable
    }

    fun setProject(project: Project) {
        this.project.set(project)
    }

    class Factory(private val application: Application, private val projectID: String) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return ProjectViewModel(application, projectID) as T
        }
    }
}