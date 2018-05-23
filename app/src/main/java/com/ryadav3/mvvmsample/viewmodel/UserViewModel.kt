package com.ryadav3.mvvmsample.viewmodel

import android.arch.lifecycle.LiveData
import android.arch.lifecycle.ViewModel
import android.arch.lifecycle.ViewModelProvider
import android.content.Context
import com.ryadav3.mvvmsample.service.model.User
import com.ryadav3.mvvmsample.service.repository.UserRepository
import com.ryadav3.mvvmsample.service.room.UserDatabase

class UserViewModel(val context: Context): ViewModel() {

    fun loginUser(email: String, password: String): LiveData<User>{
        return UserRepository.getInstance(context).login(email, password)
    }

    fun registerUser(user: User): LiveData<User>{
        return UserRepository.getInstance(context).register(user)
    }

    override fun onCleared() {
        super.onCleared()
    }

    class Factory(val context: Context) : ViewModelProvider.NewInstanceFactory() {

        override fun <T : ViewModel> create(modelClass: Class<T>): T {

            return UserViewModel(context) as T
        }
    }
}