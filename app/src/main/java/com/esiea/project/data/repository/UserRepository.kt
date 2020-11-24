package com.esiea.project.data.repository

import com.esiea.project.data.local.DatabaseDao
import com.esiea.project.data.local.models.UserLocal
import com.esiea.project.data.local.models.toData
import com.esiea.project.data.local.models.toEntity
import com.esiea.project.domain.entity.User

class UserRepository(
    private val databaseDao: DatabaseDao
) {
    suspend fun createUser(user: User) {
        databaseDao.insert(user.toData())
    }

    fun getUser(email: String): User {
        val userLocal = databaseDao.findByName(email)
        return userLocal.toEntity()
    }
}