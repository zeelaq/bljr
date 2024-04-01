package com.example.submission1.favorite

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.submission1.data.local.DatabaseConfig

class FavoriteViewModel(private val DataBaseComponent: DatabaseConfig) : ViewModel() {

    fun getFavoriteUser() = DataBaseComponent.userDao.loadAll()

    class Factory(private val database: DatabaseConfig) : ViewModelProvider.NewInstanceFactory() { //properti penyimpanan
        override fun <T : ViewModel> create(modelClass: Class<T>): T = FavoriteViewModel(database) as T //override dari viewmodelprovider
    }
}