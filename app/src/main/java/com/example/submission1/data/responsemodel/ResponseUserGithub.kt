package com.example.submission1.data.responsemodel

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

data class ResponseUserGithub(
    val incomplete_results: Boolean,
    val items: MutableList<Item>,
    val total_count: Int
) {
    @Parcelize
    @Entity(tableName = "user_github")
    data class Item(

        @PrimaryKey
        val id: Int,

        @ColumnInfo(name = "avatar_url")
        val avatar_url: String,

        @ColumnInfo(name = "followers_url")
        val followers_url: String,

        @ColumnInfo(name = "following_url")
        val following_url: String,

        @ColumnInfo(name = "login")
        val login: String

    ) : Parcelable
}
