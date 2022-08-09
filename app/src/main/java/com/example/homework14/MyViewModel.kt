package com.example.homework14


import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.lang.Exception

typealias MyList = List<Data.Content>
class MyViewModel : ViewModel() {
    val flow: Flow<Resource<MyList>> = flow {
        emit(Resource.Load())
        val state: Resource<MyList> = try {
            val response = RetrofitClient.service().getInfo()
            if (response.isSuccessful) {
                Resource.Success(response.body()!!.content)
            } else {
                Resource.Error(response.errorBody().toString())
            }
        } catch (e: Exception) {
            Resource.Error(e.toString())
        }
        emit(state)

    }


}

sealed class Resource<T> {
    data class Success<T>(val body: T) : Resource<T>()
    data class Error<T>(val errorMessage: String) : Resource<T>()
    class Load<T> : Resource<T>()
}