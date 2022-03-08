package br.com.jm.livechat.flows.onboarding

import androidx.lifecycle.ViewModel
import br.com.jm.livechat.storage.LocalStorage
import br.com.jm.livechat.storage.LocalStorage.Companion.FIRST_ACCESS_KEY

class SignUpViewModel constructor(
    private val storage: LocalStorage
): ViewModel() {
    fun isFirstAccess(): Boolean = storage.getBoolean(FIRST_ACCESS_KEY)

    fun updateFirstAccess() {
        storage.saveBoolean(FIRST_ACCESS_KEY, false)
    }

}