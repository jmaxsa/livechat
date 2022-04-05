package br.com.jm.livechat.flows.onboarding

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import br.com.jm.livechat.R
import br.com.jm.livechat.flows.home.HomeActivity
import br.com.jm.livechat.models.UserRegisterBody
import br.com.jm.livechat.network.Status
import com.firebase.ui.auth.AuthUI
import com.firebase.ui.auth.FirebaseAuthUIActivityResultContract
import com.firebase.ui.auth.data.model.FirebaseAuthUIAuthenticationResult
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity(R.layout.activity_sign_up) {

    private val viewModel by viewModel<SignUpViewModel>()

    private val nameInput by lazy { findViewById<TextInputLayout>(R.id.name_input) }
    private val emailInput by lazy { findViewById<TextInputLayout>(R.id.email_input) }
    private val phoneInput by lazy { findViewById<TextInputLayout>(R.id.phone_input) }
    private val passwordInput by lazy { findViewById<TextInputLayout>(R.id.password_input) }
    private val signUpButton by lazy { findViewById<Button>(R.id.sign_up_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponents()
    }

    private fun setupComponents() {
        nameInput.requestFocus()
        signUpButton.setOnClickListener {
            observeRequestResult(
                UserRegisterBody(
                    name = nameInput.editText?.text.toString(),
                    email = emailInput.editText?.text.toString(),
                    phone = phoneInput.editText?.text.toString(),
                    password = passwordInput.editText?.text.toString()
                )
            )
        }
    }

    private fun observeRequestResult(userRegisterBody: UserRegisterBody) {
        viewModel.registerUser(userRegisterBody).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        resource.data?.let { user -> println(user) }
                        goHome()
                    }
                    Status.ERROR -> {
                        println("LOG:::"+ it.message)
                    }
                    Status.LOADING -> {
                        Toast.makeText(this, "Carregando", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        })
    }

    private fun goHome() {
        startActivity(Intent(this, HomeActivity::class.java))
        finish()
    }
}