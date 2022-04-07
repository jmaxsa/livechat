package br.com.jm.livechat.flows.onboarding

import android.content.Intent
import android.os.Bundle
import android.view.View
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
import com.google.android.material.progressindicator.LinearProgressIndicator
import com.google.android.material.textfield.TextInputLayout
import com.google.firebase.auth.FirebaseAuth
import org.koin.androidx.viewmodel.ext.android.viewModel

class SignUpActivity: AppCompatActivity(R.layout.activity_sign_up) {

    private val viewModel by viewModel<SignUpViewModel>()

    private val loading by lazy { findViewById<LinearProgressIndicator>(R.id.screen_loading) }
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
            if(verifyValueFields(nameInput, emailInput, phoneInput, passwordInput)) {
                observeRequestResult(
                    UserRegisterBody(
                        name = nameInput.editText?.text.toString(),
                        email = emailInput.editText?.text.toString(),
                        phone = phoneInput.editText?.text.toString(),
                        password = passwordInput.editText?.text.toString()
                    )
                )
            } else {
                Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show()
            }
        }
    }

    private fun observeRequestResult(userRegisterBody: UserRegisterBody) {
        viewModel.registerUser(userRegisterBody).observe(this, Observer {
            it?.let { resource ->
                when (resource.status) {
                    Status.SUCCESS -> {
                        loading.visibility = View.GONE
                        resource.data?.let { user -> println(user) }
                        goHome()
                    }
                    Status.ERROR -> {
                        loading.visibility = View.GONE
                        Toast.makeText(this, "Algo deu errado!, tente novamente mais tarde", Toast.LENGTH_LONG).show()
                    }
                    Status.LOADING -> {
                        loading.visibility = View.VISIBLE
                    }
                }
            }
        })
    }

    private fun goHome() {
        startActivity(Intent(this, SignUpSuccessActivity::class.java))
        finish()
    }

    private fun verifyValueFields(vararg fields: TextInputLayout): Boolean {
        var result = true
        fields.forEach {
            if (it.editText == null) result = false
            result = when(it.editText?.text.toString()) {
                "" -> false
                else -> true
            }
        }

        return result
    }
}