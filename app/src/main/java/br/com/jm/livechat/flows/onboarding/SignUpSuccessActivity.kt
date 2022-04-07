package br.com.jm.livechat.flows.onboarding

import android.os.Bundle
import br.com.jm.livechat.R
import br.com.jm.livechat.base.BaseActivity

class SignUpSuccessActivity: BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_success_sign_up)
    }
}