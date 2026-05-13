package com.example.pawpatrol.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.api.RetrofitClient
import com.example.pawpatrol.models.user.LoginRequest
import com.example.pawpatrol.databinding.ActivityLoginBinding
import com.example.pawpatrol.models.user.LoginResponse
import com.example.pawpatrol.ui.main.MainActivity
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnLogin.setOnClickListener {

            val email = binding.etEmail.text.toString().trim()

            val password = binding.etPassword.text.toString().trim()

            if (email.isEmpty() || password.isEmpty()) {

                Toast.makeText(
                    this,
                    "Email dan password wajib diisi",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }

            loginUser(email, password)
        }
    }


    private fun loginUser(
        email: String,
        password: String
    ) {

        val request = LoginRequest(
            email = email,
            password = password
        )

        lifecycleScope.launch {

            try {

                val response =
                    RetrofitClient.create(this@LoginActivity)
                        .login(request)

                if (response.isSuccessful) {

                    val loginResponse = response.body()

                    val sharedPref = getSharedPreferences(
                        "USER_SESSION",
                        MODE_PRIVATE
                    )

                    sharedPref.edit()
                        .putString(
                            "TOKEN",
                            loginResponse?.access_token
                        )
                        .apply()

                    Toast.makeText(
                        this@LoginActivity,
                        "Login berhasil",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@LoginActivity,
                            MainActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this@LoginActivity,
                        "Login gagal",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@LoginActivity,
                    e.message,
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}