package com.example.submission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission1.data.local.DbModul

class FavoriteViewModel(private val dbModul: DbModul) : ViewModel() {

    fun getUserFavorite() = dbModul.userDao.loadAll()

    class Factory(private val db: DbModul) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(db) as T
    }
}