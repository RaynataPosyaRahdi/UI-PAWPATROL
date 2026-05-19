package com.example.pawpatrol.ui.auth.register

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.example.pawpatrol.api.ApiClient
import com.example.pawpatrol.databinding.ActivityRegisterBinding
import com.example.pawpatrol.models.user.UserCreateRequest
import com.example.pawpatrol.ui.auth.login.LoginActivity
import kotlinx.coroutines.launch

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding =
            ActivityRegisterBinding.inflate(layoutInflater)

        setContentView(binding.root)

        setupAction()
    }

    private fun setupAction() {

        binding.btnRegister.setOnClickListener {

            validateInput()
        }

        binding.tvLogin.setOnClickListener {

            startActivity(
                Intent(
                    this,
                    LoginActivity::class.java
                )
            )

            finish()
        }
    }

    private fun validateInput() {

        val nama =
            binding.etFullName.text.toString().trim()

        val email =
            binding.etEmail.text.toString().trim()

        val password =
            binding.etPassword.text.toString().trim()

        val confirmPassword =
            binding.etConfirmPassword.text.toString().trim()

        val alamat =
            binding.etAddress.text.toString().trim()

        when {

            nama.isEmpty() -> {

                binding.etFullName.error =
                    "Nama wajib diisi"

                binding.etFullName.requestFocus()
            }

            email.isEmpty() -> {

                binding.etEmail.error =
                    "Email wajib diisi"

                binding.etEmail.requestFocus()
            }

            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> {

                binding.etEmail.error =
                    "Format email tidak valid"

                binding.etEmail.requestFocus()
            }

            password.isEmpty() -> {

                binding.etPassword.error =
                    "Password wajib diisi"

                binding.etPassword.requestFocus()
            }

            confirmPassword.isEmpty() -> {

                binding.etConfirmPassword.error =
                    "Confirm password wajib diisi"

                binding.etConfirmPassword.requestFocus()
            }

            password != confirmPassword -> {

                binding.etConfirmPassword.error =
                    "Password tidak sama"

                binding.etConfirmPassword.requestFocus()
            }

            else -> {

                register(
                    nama,
                    email,
                    password,
                    alamat
                )
            }
        }
    }

    private fun register(
        nama: String,
        email: String,
        password: String,
        alamat: String
    ) {

        lifecycleScope.launch {

            try {

                val response =
                    ApiClient
                        .getApiService(this@RegisterActivity)
                        .register(
                            UserCreateRequest(
                                nama = nama,
                                email = email,
                                password = password,
                                alamat = alamat
                            )
                        )

                if (response.isSuccessful) {

                    Toast.makeText(
                        this@RegisterActivity,
                        "Register berhasil",
                        Toast.LENGTH_SHORT
                    ).show()

                    startActivity(
                        Intent(
                            this@RegisterActivity,
                            LoginActivity::class.java
                        )
                    )

                    finish()

                } else {

                    Toast.makeText(
                        this@RegisterActivity,
                        "Email sudah digunakan",
                        Toast.LENGTH_SHORT
                    ).show()
                }

            } catch (e: Exception) {

                Toast.makeText(
                    this@RegisterActivity,
                    e.message,
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }
}