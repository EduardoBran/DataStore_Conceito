package com.luizeduardobrandao.datastore.helper

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map

// Atenação ao instanciar um DataStore.
// Caso haja duas instâncias, vai falhar, pois o dataStore precisa ser único.
// Nesse caso, o melhor a se fazer, é tornar a classe singleton ou definir a variável acima da classe.
private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "preferencesds")

class PreferencesManager(private val context: Context) {

    // Recebe uma chave e armazena o valor no DataStore
    suspend fun save(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { pref -> pref[preferencesKey] = value }
    }

    // Faz a leitura do valor associada a chave **(não usado)**
    suspend fun read(key: String): String {
        val preferencesKey = stringPreferencesKey(key)
        val data = context.dataStore.data.first()

        return data[preferencesKey] ?: ""
    }

    // Remove chave e valor do DataStore **(não usado)**
    suspend fun remove(key: String, value: String) {
        val preferencesKey = stringPreferencesKey(key)
        context.dataStore.edit { pref -> pref.remove(preferencesKey) }
    }

    // O uso do Flow faz com que o código seja notificado quando houver uma alteração
    // em dataStore (comum na programação reativa).
    fun readFlow(key: String): Flow<String>{
        val prefKey = stringPreferencesKey(key)
        return context.dataStore.data.map { prefs -> prefs[prefKey] ?: "" }
    }
}