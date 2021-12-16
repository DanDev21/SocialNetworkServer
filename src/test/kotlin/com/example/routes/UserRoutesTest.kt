package com.example.routes

import com.example.di.testModule
import com.example.domain.model.request.SignUpRequest
import com.example.domain.model.response.Response
import com.example.domain.plugins.configureSerialization
import com.example.domain.util.Constants
import com.example.service.user.UserServiceImpl
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

    private val userService by inject<UserServiceImpl>()

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
                addHeader("Content-Type", "application/json")
                val signUpRequest = SignUpRequest(
                    email = "test@test.com",
                    username = "test_user",
                    password = "test_pass123"
                )
                setBody(gson.toJson(signUpRequest))
            }

            val response = gson
                .fromJson(request.response.content, Response::class.java)

            assertThat(response.isSuccessful)
                .isTrue()

            runBlocking {
                val user = userService.findByCredentials(
                    emailOrUsername = "test@test.com",
                    password = "test_pass123"
                )
                assertThat(user)
                    .isNotNull()

                assertThat(user?.email)
                    .isEqualTo("test@test.com")

                assertThat(user?.password)
                    .isEqualTo("test_pass123")
            }
        }
    }

    @Test
    fun `Sign up - email is already used - unsuccessful response`() {
        runBlocking {
            userService.add(
                email = "test@test.com",
                username = "test_user",
                password = "test_pass123"
            )
        }
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
                    email = "test@test.com",
                    username = "test_user2",
                    password = "test_pass99"
                )
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
        runBlocking {
            userService.add(
                email = "test@test.com",
                username = "test_user",
                password = "test_pass123"
            )
        }
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
                    email = "test1@test.com",
                    username = "test_user",
                    password = "test_pass99"
                )
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
                    Constants.Error.Validation.EMAIL +
                    Constants.Error.Validation.USERNAME +
                    Constants.Error.Validation.PASSWORD
                )
        }
    }
}