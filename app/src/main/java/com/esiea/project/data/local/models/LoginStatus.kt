package com.esiea.project.data.local.models

sealed class LoginStatus

data class LoginSuccess(val email: String): LoginStatus()
object LoginError: LoginStatus()
