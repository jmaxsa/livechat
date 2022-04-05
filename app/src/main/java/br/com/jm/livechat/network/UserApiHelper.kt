package br.com.jm.livechat.network

import br.com.jm.livechat.UserApi
import br.com.jm.livechat.models.UserRegisterBody

class UserApiHelper(private val userApi: UserApi) {
    suspend fun registerUser(userRegisterBody: UserRegisterBody) = userApi.registerUser(userRegisterBody)
}