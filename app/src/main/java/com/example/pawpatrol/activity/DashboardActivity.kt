package com.example.pawpatrol.activity

import android.content.Intent
import android.os.Bundle
import android.view.animation.Animation
import android.view.animation.AnimationUtils
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
        // ANIMASI
        // ==============================

        val anim: Animation =
            AnimationUtils.loadAnimation(
                this,
                R.anim.slide_in
            )

        cardFood.startAnimation(anim)
        cardGrooming.startAnimation(anim)
        cardAdopt.startAnimation(anim)

        // ==============================
        // FOOD ACTIVITY
        // ==============================

        cardFood.setOnClickListener {

            it.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {

                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .duration = 100

                    startActivity(
                        Intent(
                            this,
                            FoodActivity::class.java
                        )
                    )

                    overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                }
        }

        // ==============================
        // GROOMING ACTIVITY
        // ==============================

        cardGrooming.setOnClickListener {

            it.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {

                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .duration = 100

                    startActivity(
                        Intent(
                            this,
                            GroomingActivity::class.java
                        )
                    )

                    overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                }
        }

        // ==============================
        // ADOPT / CHECKOUT ACTIVITY
        // ==============================

        cardAdopt.setOnClickListener {

            it.animate()
                .scaleX(0.95f)
                .scaleY(0.95f)
                .setDuration(100)
                .withEndAction {

                    it.animate()
                        .scaleX(1f)
                        .scaleY(1f)
                        .duration = 100

                    startActivity(
                        Intent(
                            this,
                            CheckoutActivity::class.java
                        )
                    )

                    overridePendingTransition(
                        android.R.anim.fade_in,
                        android.R.anim.fade_out
                    )
                }
        }
    }
}