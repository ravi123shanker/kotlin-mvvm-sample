package com.ryadav3.mvvmsample.view.ui

import android.arch.lifecycle.Observer
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProviders
import android.databinding.DataBindingUtil
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import com.ryadav3.mvvmsample.R
import com.ryadav3.mvvmsample.databinding.ActivityRegisterBinding
import com.ryadav3.mvvmsample.service.model.User
import com.ryadav3.mvvmsample.view.callback.RegisterActivityCallback
import com.ryadav3.mvvmsample.viewmodel.UserViewModel
import es.dmoral.toasty.Toasty

import kotlinx.android.synthetic.main.activity_register.*

class RegisterActivity : AppCompatActivity(), RegisterActivityCallback {
    lateinit var activityRegisterBinding: ActivityRegisterBinding
    lateinit var userViewModel: UserViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activityRegisterBinding=DataBindingUtil.setContentView(this, R.layout.activity_register)
        activityRegisterBinding.registerActivityCallback=this
        userViewModel=ViewModelProviders.of(this, UserViewModel.Factory(this)).get(UserViewModel::class.java)

    }

    override fun onRegisterClick(view: View) {
        observeRegister(User(activityRegisterBinding.edtName.text.toString(),
                            activityRegisterBinding.edtEmail.text.toString(),
                            activityRegisterBinding.edtMobile.text.toString(),
                            activityRegisterBinding.edtPassword.text.toString()))
    }

    private fun observeRegister(user: User){
        userViewModel?.registerUser(user).observe(this, Observer { user->
            if (user != null) {
                Toasty.success(applicationContext, "Register Success", Toast.LENGTH_SHORT).show()
            }else{
                Toasty.error(applicationContext, "Register Failed", Toast.LENGTH_SHORT).show()
            }
        })
    }

}
