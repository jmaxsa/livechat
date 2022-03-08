package br.com.jm.livechat.storage

import android.content.Context
import android.content.Context.MODE_PRIVATE

class LocalStorage(private val context: Context) {
    companion object {
        const val FIRST_ACCESS_KEY = "first_access"
    }

    private val storage = context.getSharedPreferences("livechatStorage", MODE_PRIVATE)

    fun saveString(key: String, value: String) {
        storage.edit().putString(key, value).apply()
    }

    fun getString(key: String): String? = storage.getString(key, "")

    fun saveBoolean(key: String, value: Boolean) {
        storage.edit().putBoolean(key, value).apply()
    }

    fun getBoolean(key: String): Boolean = storage.getBoolean(key, true)
}