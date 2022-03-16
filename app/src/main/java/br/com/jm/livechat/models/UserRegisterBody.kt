package br.com.jm.livechat.models

data class UserRegisterBody(
    val name: String,
    val email: String,
    val phone: String,
    val password: String
)
