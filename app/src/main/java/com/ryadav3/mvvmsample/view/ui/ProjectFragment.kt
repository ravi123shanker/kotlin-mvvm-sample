package com.ryadav3.mvvmsample.view.ui

import android.arch.lifecycle.Observer
import android.support.v4.app.Fragment
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.annotation.Nullable
import android.view.ViewGroup
import android.view.LayoutInflater
import android.view.View
import com.ryadav3.mvvmsample.R
import com.ryadav3.mvvmsample.service.model.Project
import com.ryadav3.mvvmsample.viewmodel.ProjectViewModel
import android.arch.lifecycle.ViewModelProviders
import com.ryadav3.mvvmsample.databinding.FragmentProjectDetailsBinding


class ProjectFragment: Fragment() {
    private var binding: FragmentProjectDetailsBinding? = null

    @Nullable
    override fun onCreateView(inflater: LayoutInflater, @Nullable container: ViewGroup?,
                              @Nullable savedInstanceState: Bundle?): View? {
        // Inflate this data binding layout
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_details, container, false)

        // Create and set the adapter for the RecyclerView.
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val factory = ProjectViewModel.Factory(
                activity!!.application, arguments!!.getString(KEY_PROJECT_ID)!!)

        val viewModel = ViewModelProviders.of(this, factory)
                .get(ProjectViewModel::class.java)

        binding?.projectViewModel=viewModel
        binding?.isLoading=true

        observeViewModel(viewModel)
    }

    private fun observeViewModel(viewModel: ProjectViewModel) {
        // Observe project data
        viewModel.getObservableProject().observe(this, Observer<Project> { project ->
            if (project != null) {
                binding?.isLoading=false
                viewModel.setProject(project)
            }
        })
    }

    companion object {
        private val KEY_PROJECT_ID = "project_id"
        /** Creates project fragment for specific project ID  */
        @JvmStatic
        fun forProject(projectID: String): ProjectFragment {
            val fragment = ProjectFragment()
            val args = Bundle()

            args.putString(KEY_PROJECT_ID, projectID)
            fragment.arguments = args

            return fragment
        }
    }
}