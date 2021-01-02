package com.esiea.project.data.local.models

sealed class RegisterStatus

data class RegisterSuccess(val email: String): RegisterStatus()
object RegisterError: RegisterStatus()