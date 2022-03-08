package br.com.jm.livechat

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jm.livechat.storage.LocalStorage
import br.com.jm.livechat.storage.LocalStorage.Companion.FIRST_ACCESS_KEY
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth

class SignUpActivity: AppCompatActivity(R.layout.activity_sign_up) {

    private lateinit var storage: LocalStorage

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        storage = LocalStorage(this)
        createSignInIntent()
    }

    private fun isFirstAccess(): Boolean = storage.getBoolean(FIRST_ACCESS_KEY)

    private fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun createSignInIntent() {
        if(!isFirstAccess()) {
            goHome()
        }
        val providers = arrayListOf(
            AuthUI.IdpConfig.PhoneBuilder().build()
        )

        val signInIntent = AuthUI.getInstance()
            .createSignInIntentBuilder()
            .setAvailableProviders(providers)
            .build()
        signInLauncher.launch(signInIntent)
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            //TODO Save user in local protected session
            FirebaseAuth.getInstance().currentUser.let { storage.saveBoolean(FIRST_ACCESS_KEY, false) }
            goHome()
        } else {
            //TODO Handle error code in response
//            when (response?.error?.errorCode)
        }
    }
}