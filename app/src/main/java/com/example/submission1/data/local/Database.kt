package com.example.submission1.data.local

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.submission1.data.responsemodel.ResponseUserGithub

@Database(entities = [ResponseUserGithub.Item::class], version = 2, exportSchema = false) //digunakan untuk konfigurasi database
abstract class AppDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao

    companion object {
        private const val DATABASE_NAME = "AppDatabase.db"

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getDatabase(context: Context): AppDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    AppDatabase::class.java,
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
