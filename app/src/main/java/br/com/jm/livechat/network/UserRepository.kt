package br.com.jm.livechat.network

import br.com.jm.livechat.models.UserRegisterBody

class UserRepository(private val userApiHelper: UserApiHelper) {
    suspend fun registerUser(userRegisterBody: UserRegisterBody) = userApiHelper.registerUser(userRegisterBody)
}