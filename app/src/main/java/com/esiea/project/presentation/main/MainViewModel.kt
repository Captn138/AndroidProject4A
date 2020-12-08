package com.esiea.project.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.project.domain.entity.User
import com.esiea.project.domain.usecase.CreateUserUseCase
import com.esiea.project.domain.usecase.GetUserUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class MainViewModel(
    private val createUserUseCase: CreateUserUseCase,
    private val getUserUseCase: GetUserUseCase
) : ViewModel() {
    val loginLiveData : MutableLiveData<LoginStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser)
            val loginStatus = if (user != null) {
                LoginSuccess(user.email)
            } else {
                LoginError
            }
            withContext(Dispatchers.Main) {
                loginLiveData.value = loginStatus
            }
        }
    }
}