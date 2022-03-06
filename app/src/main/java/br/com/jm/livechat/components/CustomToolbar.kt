package br.com.jm.livechat.components

import android.content.Context
import android.util.AttributeSet
import androidx.appcompat.widget.Toolbar
import br.com.jm.livechat.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyle: Int = 0,
): Toolbar(context, attrs, defStyle) {

    init {
        inflate(context, R.layout.layout_custom_toolbar, this)
    }
}