package br.com.jm.livechat

import br.com.jm.livechat.models.User
import br.com.jm.livechat.models.UserRegisterBody
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface UserApi {
    companion object { private const val USER_PATH = "/user" }

    @POST("${USER_PATH}/register")
    suspend fun registerUser(@Body body: UserRegisterBody): User
}