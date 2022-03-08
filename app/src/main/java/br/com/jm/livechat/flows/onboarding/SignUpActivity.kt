package br.com.jm.livechat.flows.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jm.livechat.R
import br.com.jm.livechat.flows.home.HomeActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity(R.layout.activity_sign_up) {

    private val viewModel by viewModel<SignUpViewModel>()

    private val signInLauncher = registerForActivityResult(
        FirebaseAuthUIActivityResultContract()
    ) { res ->
        this.onSignInResult(res)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        createSignInIntent()
    }

    private fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }

    private fun createSignInIntent() {
        if(!viewModel.isFirstAccess()) goHome()
        else {
            val providers = arrayListOf(
                AuthUI.IdpConfig.PhoneBuilder().build()
            )

            val signInIntent = AuthUI.getInstance()
                .createSignInIntentBuilder()
                .setAvailableProviders(providers)
                .build()
            signInLauncher.launch(signInIntent)
        }
    }

    private fun onSignInResult(result: FirebaseAuthUIAuthenticationResult) {
        val response = result.idpResponse
        if (result.resultCode == RESULT_OK) {
            FirebaseAuth.getInstance().currentUser.let { viewModel.updateFirstAccess() }
            goHome()
        } else {
            //TODO Handle error code in response
//            when (response?.error?.errorCode)
        }
    }
}