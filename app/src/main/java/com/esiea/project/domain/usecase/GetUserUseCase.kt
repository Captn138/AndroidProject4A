package com.esiea.project.domain.usecase

import com.esiea.project.data.repository.UserRepository
import com.esiea.project.domain.entity.User

class GetUserUseCase(
    private val userRepository: UserRepository
) {
    suspend fun invoke(email: String): User? {
        return userRepository.getUser(email)
    }
}