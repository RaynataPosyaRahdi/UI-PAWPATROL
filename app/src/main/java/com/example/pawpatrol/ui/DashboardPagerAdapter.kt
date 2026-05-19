package com.example.pawpatrol.ui

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.pawpatrol.ui.fragment.PetAdoptFragment
import com.example.pawpatrol.ui.fragment.ProductFragment


class DashboardPagerAdapter(
    fragmentActivity: FragmentActivity
) : FragmentStateAdapter(fragmentActivity) {

    override fun getItemCount(): Int = 2

    override fun createFragment(position: Int): Fragment {

        return when(position) {

            0 -> PetAdoptFragment()

            else -> ProductFragment()
        }
    }
}