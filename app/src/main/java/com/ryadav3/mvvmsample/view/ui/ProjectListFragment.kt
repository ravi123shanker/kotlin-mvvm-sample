package com.ryadav3.mvvmsample.view.ui

import android.support.v4.app.Fragment
import com.ryadav3.mvvmsample.view.adapter.ProjectAdapter
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.ryadav3.mvvmsample.R
import android.arch.lifecycle.Lifecycle
import android.arch.lifecycle.Observer
import com.ryadav3.mvvmsample.view.callback.ProjectClickCallback
import android.arch.lifecycle.ViewModelProviders
import com.ryadav3.mvvmsample.databinding.FragmentProjectListBinding
import com.ryadav3.mvvmsample.service.model.Project
import com.ryadav3.mvvmsample.viewmodel.ProjectListViewModel

class ProjectListFragment: Fragment() {
    private var projectAdapter: ProjectAdapter? = null
    private var binding: FragmentProjectListBinding? = null
    private var projectList= mutableListOf<Project>()
    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false)

        projectAdapter = ProjectAdapter(projectList, projectClickCallback)
        binding?.projectList?.adapter = projectAdapter
        binding?.isLoading = true

        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val viewModel = ViewModelProviders.of(this).get(ProjectListViewModel::class.java)

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectListViewModel) {
        // Update the list when the data changes
        viewModel.getProjectListObservable().observe(this, Observer<List<Project>> { projects ->
            if (projects != null) {
                binding?.isLoading=false
                projectList.clear()
                projectList.addAll(projects.toMutableList())
                projectAdapter?.notifyDataSetChanged()
            }
        })
    }

    private val projectClickCallback = object : ProjectClickCallback {
        override fun onClick(view: View, project: Project) {
            if (lifecycle.currentState.isAtLeast(Lifecycle.State.STARTED)) {
                (activity as MainActivity).show(project)
            }
        }
    }
    companion object {
        val TAG = "ProjectListFragment"
    }
}