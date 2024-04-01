package com.example.submission1.data.local

import android.content.Context
import androidx.room.Room

//untuk mengatur konfigurasi database
class DatabaseConfig(private val context: Context) { //builder database
    private val dbConfig = Room.databaseBuilder(context, Database::class.java, "githubuser")
        .allowMainThreadQueries()
        .build()

    val userDao = dbConfig.userDao()
}