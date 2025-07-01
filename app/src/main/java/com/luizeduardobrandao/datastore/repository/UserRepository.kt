package com.luizeduardobrandao.datastore.repository

import com.luizeduardobrandao.datastore.helper.PreferencesManager
import kotlinx.coroutines.flow.Flow

class UserRepository(private val prefs: PreferencesManager) {
    companion object {
        private const val USER_NAME_KEY = "username"
    }

    fun getUserNameFlow(): Flow<String> = prefs.readFlow(USER_NAME_KEY)

    suspend fun saveUserName(name: String) = prefs.save(USER_NAME_KEY, name)
}