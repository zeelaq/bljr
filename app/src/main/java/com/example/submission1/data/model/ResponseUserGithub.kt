package com.example.submission1.data.model

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
    @Entity(tableName = "user")
    data class Item(
        @ColumnInfo(name = "avatar_url")
        val avatar_url: String,
        val events_url: String,
        @ColumnInfo(name = "followers_url")
        val followers_url: String,
        @ColumnInfo(name = "following_url")
        val following_url: String,
        val gists_url: String,
        val gravatar_id: String,
        val html_url: String,
        @PrimaryKey
        val id: Int,
        @ColumnInfo(name = "login")
        val login: String,
        val node_id: String,
        val organizations_url: String,
        val received_events_url: String,
        val repos_url: String,
        val score: Double,
        val site_admin: Boolean,
        val starred_url: String,
        val subscriptions_url: String,
        val type: String,
        val url: String
    ) : Parcelable
}
