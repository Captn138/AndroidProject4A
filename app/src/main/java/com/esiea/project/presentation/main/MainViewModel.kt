package com.esiea.project.presentation.main

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.esiea.project.data.local.models.*
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
    val registerLiveData: MutableLiveData<RegisterStatus> = MutableLiveData()

    fun onClickedLogin(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            val user = getUserUseCase.invoke(emailUser, password)
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

    fun onClickedRegister(emailUser: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            var flag = false
            val user = User(emailUser, password)
            if(emailUser != "" && password != "") {
                createUserUseCase.invoke(user)
                flag = true
            }
            if(getUserUseCase.invoke(emailUser, password) != null) {
                flag = true
            }
            val registerStatus = if (flag == true) {
                RegisterSuccess(user.email)
            } else {
                RegisterError
            }
            withContext(Dispatchers.Main) {
                registerLiveData.value = registerStatus
            }
        }
    }
}