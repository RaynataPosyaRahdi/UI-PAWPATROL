package com.example.pawpatrol.ui.auth.login

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.ui.DashboardActivity
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityLoginBinding
import com.example.pawpatrol.models.user.LoginRequest
import com.example.pawpatrol.session.SessionManager
import com.example.pawpatrol.ui.auth.register.RegisterActivity
import kotlinx.coroutines.launch

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding

    private lateinit var sessionManager: SessionManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityLoginBinding.inflate(layoutInflater)

        setContentView(binding.root)

        sessionManager =
            SessionManager(this)

        setupAction()
    }

    private fun setupAction() {

        binding.btnLogin.setOnClickListener {

            val email =
                binding.etEmail.text.toString()

            val password =
                binding.etPassword.text.toString()

            login(
                email,
                password
            )
        }

        binding.tvRegister.setOnClickListener {
            startActivity(
                Intent(
                    this,
                    RegisterActivity::class.java
                )
            )
        }
    }

    private fun login(
        email: String,
        password: String
    ) {

        lifecycleScope.launch {

            try {

                val response =
                    ApiClient
                        .getApiService(this@LoginActivity)
                        .login(
                            LoginRequest(
                                email,
                                password
                            )
                        )

                if (response.isSuccessful) {

                    val body = response.body()

                    body?.let {

                        sessionManager.saveToken(
                            it.access_token
                        )

                        sessionManager.saveUserId(
                            it.user.id
                        )

                        Log.d(
                            "TOKEN",
                            sessionManager.getToken().toString()
                        )

                        Toast.makeText(
                            this@LoginActivity,
                            "Login berhasil",
                            Toast.LENGTH_SHORT
                        ).show()

                        startActivity(
                            Intent(
                                this@LoginActivity,
                                DashboardActivity::class.java
                            )
                        )

                        finish()
                    }

                } else {

                    Toast.makeText(
                        this@LoginActivity,
                        "Email atau password salah",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@LoginActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}