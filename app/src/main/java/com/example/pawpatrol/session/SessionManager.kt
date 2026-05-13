package com.example.pawpatrol.session

import android.content.Context

class SessionManager(context: Context) {

    private val prefs =
        context.getSharedPreferences(
            "petshop_session",
            Context.MODE_PRIVATE
        )

    companion object {

        private const val KEY_TOKEN = "TOKEN"

    }

    fun saveToken(token: String) {

        prefs.edit()
            .putString(KEY_TOKEN, token)
            .apply()
    }

    fun getToken(): String? {

        return prefs.getString(KEY_TOKEN, null)

    }

    fun clearSession() {

        prefs.edit().clear().apply()

    }

    fun isLogin(): Boolean {

        return getToken() != null

    }
}