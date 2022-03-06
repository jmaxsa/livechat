package br.com.jm.livechat

import android.os.Bundle
import br.com.jm.livechat.base.BaseActivity

class WelcomeActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)
    }
}