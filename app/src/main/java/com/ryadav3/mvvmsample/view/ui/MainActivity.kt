package com.ryadav3.mvvmsample.view.ui

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.ryadav3.mvvmsample.R
import com.ryadav3.mvvmsample.service.model.Project



class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // Add project list fragment if this is first creation
        if (savedInstanceState == null) {
            val fragment = ProjectListFragment()

            supportFragmentManager.beginTransaction()
                    .add(R.id.fragment_container, fragment, ProjectListFragment.TAG).commit()
        }
    }

    fun show(project: Project) {
        val projectFragment = ProjectFragment.forProject(project.name)

        supportFragmentManager
                .beginTransaction()
                .addToBackStack("project")
                .replace(R.id.fragment_container,
                        projectFragment, null).commit()
    }
}
