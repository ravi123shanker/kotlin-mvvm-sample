package com.ryadav3.mvvmsample.service.model

import android.arch.persistence.room.Entity
import android.arch.persistence.room.Ignore
import android.arch.persistence.room.PrimaryKey

@Entity(tableName = "user")
data class User(
        val userName: String="",
        @PrimaryKey
        val email: String="",
        val mobile: String="",
        val password: String=""
){

        @Ignore
        constructor() : this("", "", "", "")
}