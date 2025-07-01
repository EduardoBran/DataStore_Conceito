package com.luizeduardobrandao.datastore.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.asLiveData
import com.luizeduardobrandao.datastore.helper.PreferencesManager
import com.luizeduardobrandao.datastore.repository.UserRepository

class UsernameViewModel(application: Application): AndroidViewModel(application) {

    private val repo = UserRepository(PreferencesManager(getApplication()))

    val userNameLiveData = repo.getUserNameFlow().asLiveData()
}