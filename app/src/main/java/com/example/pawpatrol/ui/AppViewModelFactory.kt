package com.example.pawpatrol.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

import com.example.pawpatrol.repository.*

import com.example.pawpatrol.ui.animal.AnimalViewModel
import com.example.pawpatrol.ui.auth.login.LoginViewModel
import com.example.pawpatrol.ui.auth.register.RegisterViewModel
import com.example.pawpatrol.ui.grooming.GroomingViewModel
import com.example.pawpatrol.ui.cart.OrderViewModel
import com.example.pawpatrol.ui.product.ProductViewModel

class AppViewModelFactory(

    private val authRepository: AuthRepository,

    private val animalRepository: AnimalRepository,

    private val productRepository: ProductRepository,

    private val groomingRepository: GroomingRepository,

    private val orderRepository: OrderRepository

) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(
        modelClass: Class<T>
    ): T {

        return when {

            modelClass.isAssignableFrom(
                LoginViewModel::class.java
            ) -> {

                LoginViewModel(
                    authRepository
                ) as T
            }

            modelClass.isAssignableFrom(
                RegisterViewModel::class.java
            ) -> {

                RegisterViewModel(
                    authRepository
                ) as T
            }

            modelClass.isAssignableFrom(
                AnimalViewModel::class.java
            ) -> {

                AnimalViewModel(
                    animalRepository
                ) as T
            }

            modelClass.isAssignableFrom(
                ProductViewModel::class.java
            ) -> {

                ProductViewModel(
                    productRepository
                ) as T
            }

            modelClass.isAssignableFrom(
                GroomingViewModel::class.java
            ) -> {

                GroomingViewModel(
                    groomingRepository
                ) as T
            }

            modelClass.isAssignableFrom(
                OrderViewModel::class.java
            ) -> {

                OrderViewModel(
                    orderRepository
                ) as T
            }

            else -> {

                throw IllegalArgumentException(
                    "Unknown ViewModel"
                )
            }
        }
    }
}