package com.example.submission1.data.local

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.submission1.data.responsemodel.ResponseUserGithub

@Dao
interface UserDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(user: ResponseUserGithub.Item) //Agar id data lama diganti data baru

    @Query("Select * From user_github")
    fun loadAll(): LiveData<MutableList<ResponseUserGithub.Item>> //untuk memanggil semua data pengguna "user github"

    @Query("Select * From user_github Where id Like :id")
    fun findById(id: Int): ResponseUserGithub.Item // saya menggunakan ini agar pengguna dapat mencari akun github orang menggunakan id

    @Delete
    fun delete(user_github: ResponseUserGithub.Item)

    @Update
    fun update(user: ResponseUserGithub.Item)
}