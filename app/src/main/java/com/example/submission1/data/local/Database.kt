package com.example.submission1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission1.data.responsemodel.ResponseUserGithub

@Database(entities = [ResponseUserGithub.Item::class], version = 5, exportSchema = false) //digunakan untuk konfigurasi database
abstract class Database : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "Database.db"

        @Volatile
        private var INSTANCE: com.example.submission1.data.local.Database? = null

        fun getDatabase(context: Context): com.example.submission1.data.local.Database {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    com.example.submission1.data.local.Database::class.java,
                    DATABASE_NAME
                )
                    .fallbackToDestructiveMigration() // Menghindari kehilangan data
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }

}
