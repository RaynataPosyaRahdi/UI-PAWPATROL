package com.example.pawpatrol.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.example.pawpatrol.R

class DashboardActivity : AppCompatActivity() {

    private lateinit var cardFood: CardView
    private lateinit var cardGrooming: CardView
    private lateinit var cardAdopt: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        // ==============================
        // INIT CARD
        // ==============================

        cardFood = findViewById(R.id.cardFood)
        cardGrooming = findViewById(R.id.cardGrooming)
        cardAdopt = findViewById(R.id.cardAdopt)

        // ==============================
        // PINDAH KE FOOD ACTIVITY
        // ==============================

        cardFood.setOnClickListener {

            val intent = Intent(
                this,
                FoodActivity::class.java
            )

            startActivity(intent)
        }

        // ==============================
        // PINDAH KE GROOMING ACTIVITY
        // ==============================

        cardGrooming.setOnClickListener {

            val intent = Intent(
                this,
                GroomingActivity::class.java
            )

            startActivity(intent)
        }

        // ==============================
        // PINDAH KE CHECKOUT / ADOPT
        // ==============================

        cardAdopt.setOnClickListener {

            val intent = Intent(
                this,
                CheckoutActivity::class.java
            )

            startActivity(intent)
        }
    }
}
