package com.esiea.project.presentation.main

sealed class RegisterStatus

data class RegisterSuccess(val email: String): RegisterStatus()
object RegisterError: RegisterStatus()