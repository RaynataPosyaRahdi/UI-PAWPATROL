package com.example.pawpatrol.session

import android.content.Context

class SessionManager(
    context: Context
) {

    private val prefs =
        context.getSharedPreferences(
            "session",
            Context.MODE_PRIVATE
        )

    companion object{
        private const val TOKEN = "TOKEN"
        private const val USER_ID="USER_ID"
    }
    fun saveToken(token: String) {
        prefs.edit()
            .putString("TOKEN", token)
            .apply()
    }

    fun getToken(): String? {
        return prefs.getString(
            "TOKEN",
            null
        )
    }

    fun saveUserId(id: Int){
        prefs.edit()
            .putInt(USER_ID, id)
            .apply()
    }

    fun getUserId(): Int{
        return prefs.getInt(
            USER_ID,
            0
        )
    }

    fun clearSession() {
        prefs.edit().clear().apply()
    }
}