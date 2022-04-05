package br.com.jm.livechat.network

import br.com.jm.livechat.UserApi
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitBuilder {
    private const val BASE_URL = "https://live-chat-brasil.herokuapp.com"

    private fun getRetrofit() = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    fun provideService(): UserApi = getRetrofit().create(UserApi::class.java)
}