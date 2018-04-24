package com.ryadav3.mvvmsample.view.callback

import android.view.View
import com.ryadav3.mvvmsample.service.model.Project



interface ProjectClickCallback {
    fun onClick(view: View, project: Project)
}