package com.ryadav3.mvvmsample.service.room

import android.arch.persistence.room.*
import android.arch.persistence.room.OnConflictStrategy.FAIL
import com.ryadav3.mvvmsample.service.model.User

@Dao
interface UserDao {
    @Insert(onConflict = FAIL)
    fun insertUser(user: User)

    @Query("SELECT * FROM user where email= :email and password= :password")
    fun getUser(email: String, password: String): User

    @Query("SELECT * FROM user")
    fun getAllUser(): List<User>

    @Delete
    fun deleteUser(user: User)

    @Update
    fun updateUser(user: User)

    @Query("DELETE FROM user")
    fun deleteAllUser()

}