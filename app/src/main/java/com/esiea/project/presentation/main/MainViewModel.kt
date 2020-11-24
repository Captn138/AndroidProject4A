package com.esiea.project.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.project.domain.entity.User
import com.esiea.project.domain.usecase.CreateUserUseCase
import kotlinx.coroutines.launch

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase
) : ViewModel() {
    val counter : MutableLiveData<Int> = MutableLiveData()
    init {
        counter.value = 0
    }

    fun onClickedIncrement(emailUser : String) {
        viewModelScope.launch {
            createUserUseCase.invoke(User(emailUser))
        }
        counter.value = (counter.value ?: 0) + 1
    }
}