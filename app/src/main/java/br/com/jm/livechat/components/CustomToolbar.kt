package br.com.jm.livechat.components

import android.content.Context
import android.util.AttributeSet
import android.view.View
import android.widget.ImageButton
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import br.com.jm.livechat.R

class CustomToolbar @JvmOverloads constructor(
    context: Context,
    private val attrs: AttributeSet? = null,
    defStyle: Int = 0,
): Toolbar(context, attrs, defStyle) {

    private val searchButton by lazy { findViewById<ImageButton>(R.id.search_button) }

    override fun onViewAdded(child: View?) {
        super.onViewAdded(child)

        context.theme.obtainStyledAttributes(
            attrs,
            R.styleable.CustomToolbar,
            0, 0
        ).apply {
            try {
                searchButton.isVisible = getBoolean(R.styleable.CustomToolbar_showSearchIcon, true)
            } finally {
                recycle()
            }
        }
    }

    init {
        inflate(context, R.layout.layout_custom_toolbar, this)
    }
}