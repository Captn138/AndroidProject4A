package com.esiea.project.domain.usecase

import com.esiea.project.data.repository.UserRepository
import com.esiea.project.domain.entity.User
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import junit.framework.Assert.assertEquals
import kotlinx.coroutines.runBlocking
import org.junit.Test

class GetUserUseCaseTest {
    private val userRepository: UserRepository = mockk()
    private val classUnderTest = GetUserUseCase(userRepository)

    @Test
    fun `invoke with invalid email`() {
        runBlocking {
            //GIVEN
            val email = ""
            val password = ""
            coEvery { userRepository.getUser(email, password) } returns null
            //WHEN
            val result = classUnderTest.invoke(email, password)
            //THEN
            coVerify(exactly = 1) { userRepository.getUser(email, password) }
            assertEquals(result, null)
        }
    }

    @Test
    fun `invoke with valid email`() {
        runBlocking {
            //GIVEN
            val email = "a@a.a"
            val password = "abc"
            val expectedUser = User("a@a.a", "abc")
            coEvery { userRepository.getUser(email, password) } returns expectedUser
            //WHEN
            val result = classUnderTest.invoke(email, password)
            //THEN
            coVerify(exactly = 1) { userRepository.getUser(email, password) }
            assertEquals(result, expectedUser)
        }
    }
}