package com.example.pawpatrol.ui.animal
import androidx.lifecycle.*
import com.example.pawpatrol.repository.AnimalRepository
import com.example.pawpatrol.models.animal.Animals
import kotlinx.coroutines.launch

class AnimalViewModel(
    private val repository: AnimalRepository
) : ViewModel() {

    private val _animals =
        MutableLiveData<List<Animals>>()

    val animals:
            LiveData<List<Animals>>
            = _animals

    private val _loading =
        MutableLiveData<Boolean>()

    val loading:
            LiveData<Boolean>
            = _loading

    private val _error =
        MutableLiveData<String>()

    val error:
            LiveData<String>
            = _error

    fun fetchAnimals() {

        viewModelScope.launch {

            try {

                _loading.value = true

                val response =
                    repository.getAnimals()

                if (response.isSuccessful &&
                    response.body() != null
                ) {

                    _animals.value =
                        response.body()

                } else {

                    _error.value =
                        "Gagal mengambil data"

                }

            } catch (e: Exception) {

                _error.value =
                    e.message

            } finally {

                _loading.value = false

            }
        }
    }
}