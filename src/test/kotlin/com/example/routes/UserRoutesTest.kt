package com.example.routes

import com.example.di.testModule
import com.example.domain.model.Credential
import com.example.domain.data.dto.request.SignInRequest
import com.example.domain.data.dto.request.SignUpRequest
import com.example.domain.data.dto.response.Response
import com.example.plugins.configureSerialization
import com.example.domain.util.Constants
import com.example.service.UserService
import com.google.common.truth.Truth.assertThat
import com.google.gson.Gson
import io.ktor.application.*
import io.ktor.http.*
import io.ktor.routing.*
import io.ktor.server.testing.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import org.koin.core.context.startKoin
import org.koin.core.context.stopKoin
import org.koin.test.KoinTest
import org.koin.test.inject
import kotlin.test.AfterTest
import kotlin.test.BeforeTest

class UserRoutesTest : KoinTest {

    private val userService by inject<UserService>()

    private val gson = Gson()

    @BeforeTest
    fun setup() {
        startKoin {
            modules(testModule)
        }
    }

    @AfterTest
    fun tearDown() {
        stopKoin()
    }

    @Test
    fun `Sign up - no body attached - bad request response`() {
        withTestApplication(
            moduleFunction = {
                install(Routing) {
                    signUp(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_UP
            )
            assertThat(request.response.status())
                .isEqualTo(HttpStatusCode.BadRequest)
        }
    }

    @Test
    fun `Sign up - valid user data - successful response`() {
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signUp(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_UP
            ) {
                val signUpRequest = SignUpRequest(
                    email = "test@test.com",
                    username = "test_user",
                    password = "test_pass123"
                )
                addHeader("Content-Type", "application/json")
                setBody(gson.toJson(signUpRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isTrue()

            runBlocking {
                val credential = Credential(
                    emailOrUsername = "test@test.com",
                    password = "test_pass123"
                )
                val user = userService.signIn(credential)
                assertThat(user)
                    .isNotNull()

                assertThat(user.email)
                    .isEqualTo("test@test.com")

                assertThat(user.password)
                    .isEqualTo("test_pass123")
            }
        }
    }

    @Test
    fun `Sign up - email is already used - unsuccessful response`() {
        addDefaultUser()
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signUp(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_UP
            ) {
                val signUpRequest = SignUpRequest(
                    email = "test@test.com",
                    username = "test_user",
                    password = "test_pass123"
                )
                addHeader("Content-Type", "application/json")
                setBody(gson.toJson(signUpRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isFalse()

            assertThat(response.message)
                .isEqualTo(Constants.Error.Repository.EMAIL_TAKEN)
        }
    }

    @Test
    fun `Sign up - username is already used - unsuccessful response`() {
        addDefaultUser()
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signUp(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_UP
            ) {
                val signUpRequest = SignUpRequest(
                    email = "other@test.com",
                    username = "test_user",
                    password = "test_pass99"
                )
                addHeader("Content-Type", "application/json")
                setBody(gson.toJson(signUpRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isFalse()

            assertThat(response.message)
                .isEqualTo(Constants.Error.Repository.USERNAME_TAKEN)
        }
    }

    @Test
    fun `Sign up - invalid data - unsuccessful response`() {
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signUp(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_UP
            ) {
                addHeader("Content-Type", "application/json")
                val signUpRequest = SignUpRequest(
                    email = "    ",
                    username = "   u2       ",
                    password = "  t_pas    "
                )
                setBody(gson.toJson(signUpRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isFalse()

            assertThat(response.message)
                .contains(
                    Constants.Error.Invalid.EMAIL +
                    Constants.Error.Invalid.USERNAME +
                    Constants.Error.Invalid.PASSWORD
                )
        }
    }

    @Test
    fun `Sign in, correct credentials, successful response`() {
        addDefaultUser()
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signIn(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_IN
            ) {
                addHeader("Content-Type", "application/json")
                val signInRequest = SignInRequest(
                    emailOrUsername = "test_user",
                    password = "test_pass123"
                )
                setBody(gson.toJson(signInRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isTrue()
        }
    }

    @Test
    fun `Sign in, incorrect credentials, unsuccessful response`() {
        addDefaultUser()
        withTestApplication(
            moduleFunction = {
                configureSerialization()
                install(Routing) {
                    signIn(userService)
                }
            }
        ) {
            val request = handleRequest(
                method = HttpMethod.Post,
                uri = Constants.Routes.User.SIGN_IN
            ) {
                addHeader("Content-Type", "application/json")
                val signInRequest = SignInRequest(
                    emailOrUsername = "test_user",
                    password = "incorrect_pass"
                )
                setBody(gson.toJson(signInRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isFalse()

            assertThat(response.message)
                .isEqualTo(Constants.Error.Repository.CREDENTIALS_DO_NOT_MATCH)
        }
    }

    private fun addDefaultUser() = runBlocking {
        userService.add(
            email = "test@test.com",
            username = "test_user",
            password = "test_pass123"
        )
    }
}