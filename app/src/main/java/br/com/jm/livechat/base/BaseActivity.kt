package br.com.jm.livechat.base

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import br.com.jm.livechat.R

abstract class BaseActivity: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val rootView by lazy { findViewById<View>(android.R.id.content) }
        rootView.setBackgroundColor(ResourcesCompat.getColor(resources, R.color.blue_500, null))
    }
}