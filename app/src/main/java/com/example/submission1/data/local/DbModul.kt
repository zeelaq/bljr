package com.example.submission1.data.local

import android.content.Context
import androidx.room.Room

class DbModul(private val context: Context) {
    private val db = Room.databaseBuilder(context, AppDataBase::class.java, "usergithub.db")
        .allowMainThreadQueries()
        .build()

    val userDao = db.userDao()
}