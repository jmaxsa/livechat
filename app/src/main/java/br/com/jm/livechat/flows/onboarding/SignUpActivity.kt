package br.com.jm.livechat.flows.onboarding

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import br.com.jm.livechat.R
import br.com.jm.livechat.flows.home.HomeActivity
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity(R.layout.activity_sign_up) {

    private val viewModel by viewModel<SignUpViewModel>()

    private val nameInput by lazy { findViewById<TextInputLayout>(R.id.name_input) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponents()
    }

    private fun setupComponents() {
        nameInput.requestFocus()
    }

    private fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}