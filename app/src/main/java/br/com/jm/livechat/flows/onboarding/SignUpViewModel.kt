package br.com.jm.livechat.flows.onboarding

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.jm.livechat.UserApi
import br.com.jm.livechat.models.UserRegisterBody
import androidx.lifecycle.liveData
import br.com.jm.livechat.network.Resource
import br.com.jm.livechat.network.UserRepository
import br.com.jm.livechat.storage.LocalStorage
import br.com.jm.livechat.storage.LocalStorage.Companion.FIRST_ACCESS_KEY
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class SignUpViewModel constructor(
    private val storage: LocalStorage,
    private val repository: UserRepository
): ViewModel() {
    fun isFirstAccess(): Boolean = storage.getBoolean(FIRST_ACCESS_KEY)

    fun updateFirstAccess() {
        storage.saveBoolean(FIRST_ACCESS_KEY, false)
    }

    fun registerUser(userRegisterBody: UserRegisterBody) = liveData(Dispatchers.IO) {
        emit(Resource.loading(data = null))
        try {
            emit(Resource.success(data = repository.registerUser(userRegisterBody)))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, message = exception.message ?: "Error occurred!!"))
        }
    }
}