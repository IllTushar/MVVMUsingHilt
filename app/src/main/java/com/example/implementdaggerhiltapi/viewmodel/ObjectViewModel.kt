package com.example.implementdaggerhiltapi.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.implementdaggerhiltapi.model.ObjectsResponse
import com.example.implementdaggerhiltapi.repository.userRepo
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ObjectViewModel @Inject constructor(private val repo: userRepo) : ViewModel() {
    private val _data = MutableLiveData<ObjectsResponse>()
    val data: LiveData<ObjectsResponse> = _data

    fun fetchData() {
        viewModelScope.launch(Dispatchers.IO) {
            val result = repo.getObjects()
            result?.let {
                _data.postValue(it)
            }
        }
    }
}