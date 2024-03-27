package com.example.submission1.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.submission1.data.model.ResponseUserGithub

@Database(entities = [ResponseUserGithub.Item::class], version = 1, exportSchema = false)
abstract class AppDataBase : RoomDatabase() {
    abstract fun userDao(): UserDao
}