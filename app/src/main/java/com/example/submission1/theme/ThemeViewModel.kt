package com.example.submission1.theme

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.example.submission1.data.local.PreferencesTheme
import kotlinx.coroutines.launch

class ThemeViewModel(private val pref: PreferencesTheme) : ViewModel() {

    fun getTheme() = pref.getThemeSetting().asLiveData()

    fun saveTheme(isDark: Boolean) {
        viewModelScope.launch {
            pref.saveThemeSetting(isDark)
        }
    }

    class Factory(private val pref: PreferencesTheme) : ViewModelProvider.NewInstanceFactory() {
        override fun <T : ViewModel> create(modelClass: Class<T>): T = ThemeViewModel(pref) as T
    }
}