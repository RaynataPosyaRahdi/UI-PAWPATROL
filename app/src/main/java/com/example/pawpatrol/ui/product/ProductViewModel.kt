package com.example.pawpatrol.ui.product
import androidx.lifecycle.*
import com.example.pawpatrol.repository.ProductRepository
import com.example.pawpatrol.models.product.Products
import kotlinx.coroutines.launch

class ProductViewModel(
    private val repository: ProductRepository
) : ViewModel() {

    private val _products =
        MutableLiveData<List<Products>>()

    val products:
            LiveData<List<Products>>
            = _products

    fun fetchProducts() {

        viewModelScope.launch {

            try {

                val response =
                    repository.getProducts()

                if (response.isSuccessful &&
                    response.body() != null
                ) {

                    _products.value =
                        response.body()

                }

            } catch (e: Exception) {

                e.printStackTrace()

            }
        }
    }
}