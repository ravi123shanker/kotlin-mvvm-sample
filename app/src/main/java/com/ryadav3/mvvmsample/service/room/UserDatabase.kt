package com.ryadav3.mvvmsample.service.room

import android.arch.persistence.room.Database
import android.arch.persistence.room.Room
import android.arch.persistence.room.RoomDatabase
import android.content.Context
import com.ryadav3.mvvmsample.service.model.User

@Database(entities = arrayOf(User::class), version = 1)
abstract class UserDatabase: RoomDatabase() {
    abstract fun userDao(): UserDao
}