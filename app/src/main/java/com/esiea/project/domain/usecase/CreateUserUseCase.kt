package com.esiea.project.domain.usecase

import com.esiea.project.data.repository.UserRepository
import com.esiea.project.domain.entity.User

class CreateUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(user : User) {
        userRepository.createUser(user)
    }
}