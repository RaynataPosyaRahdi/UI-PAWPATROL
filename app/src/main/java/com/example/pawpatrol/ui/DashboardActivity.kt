package com.example.pawpatrol.ui

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.pawpatrol.databinding.ActivityDashboardBinding
import com.example.pawpatrol.ui.animal.AddAnimalActivity
import com.example.pawpatrol.ui.product.AddProductActivity
import com.google.android.material.tabs.TabLayoutMediator
import kotlin.jvm.java


class DashboardActivity : AppCompatActivity() {

    private lateinit var pagerAdapter: DashboardPagerAdapter
    private lateinit var binding: ActivityDashboardBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityDashboardBinding.inflate(layoutInflater)
        setContentView(binding.root)

        pagerAdapter = DashboardPagerAdapter(this)

        binding.viewPager.adapter = pagerAdapter

        TabLayoutMediator(
            binding.tabLayout,
            binding.viewPager
        ) { tab, position ->

            when (position) {

                0 -> tab.text = "Pet Adopt"

                1 -> tab.text = "Product"
            }

        }.attach()

        binding.fabCart.setOnClickListener {
            when (binding.viewPager.currentItem) {

                0 -> {
                    startActivity(
                        Intent(
                            this,
                            AddAnimalActivity::class.java
                        )
                    )
                }

                1 -> {
                    // TAB PRODUCT
                    startActivity(
                        Intent(
                            this,
                            AddProductActivity::class.java
                        )
                    )
                }
            }
        }
    }
}