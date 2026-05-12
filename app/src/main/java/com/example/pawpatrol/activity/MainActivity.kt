package com.example.pawpatrol.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // pindah ke dashboard
        startActivity(
            Intent(this, DashboardActivity::class.java)
        )

        finish()
    }
}