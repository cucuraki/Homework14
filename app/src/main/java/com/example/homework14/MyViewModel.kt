package com.example.homework14


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import retrofit2.Response
import java.lang.Exception

class MyViewModel : ViewModel() {
    private lateinit var list: List<Data.Content>
    private val defaultDispatcher: CoroutineDispatcher = Dispatchers.Default
    private val _flaw = MutableStateFlow(listOf<Data.Content>())
    val state: StateFlow<List<Data.Content>> = _flaw
    fun getInfo() {
        viewModelScope.launch(defaultDispatcher) {

            val response = try {
                RetrofitClient.service().getInfo()
            } catch (e: Exception) {
                e.toString()
            }
            when (response) {
                is Response<*> -> {
                    if (response.isSuccessful) {
                        _flaw.value = (response.body() as Data).content
                    }else{

                    }
                }
                is String -> {

                }
            }
        }
    }
}