package com.ryadav3.mvvmsample.service.repository

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.MutableLiveData
import android.content.Context
import com.ryadav3.mvvmsample.app.MyApp
import com.ryadav3.mvvmsample.service.model.User

class UserRepository {
    companion object {
        private var loginRepository: UserRepository? = null
        private var context: Context?=null
        @Synchronized
        @JvmStatic
        fun getInstance(context: Context): UserRepository {
            this.context=context
            if (loginRepository == null) loginRepository = UserRepository()
            return loginRepository!!
        }
    }

    fun login(email: String, password: String): LiveData<User>{
        val loginData=MutableLiveData<User>()
        val user=MyApp.database?.userDao()?.getUser(email, password)
        loginData.value=user
        return loginData
    }

    fun register(user: User): LiveData<User>{
        val registerData=MutableLiveData<User>()
        MyApp.database?.userDao()?.insertUser(user)
        registerData.value=user
        return registerData
    }
}