package com.luizeduardobrandao.datastore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.luizeduardobrandao.datastore.helper.PreferencesManager
import com.luizeduardobrandao.datastore.repository.UserRepository
import kotlinx.coroutines.launch

class MainViewModel(application: Application): AndroidViewModel(application) {

    private val repo = UserRepository(PreferencesManager(getApplication()))

    val userNameLiveData = repo.getUserNameFlow().asLiveData()

    fun saveUserName(name: String) {
        viewModelScope.launch {
            repo.saveUserName(name)
        }
    }
}