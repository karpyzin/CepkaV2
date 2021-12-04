package ru.karpyzin.cepka.view.widgets

import android.app.Activity
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.FrameLayout
import androidx.annotation.ColorRes
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import com.google.android.material.snackbar.Snackbar
import ru.karpyzin.cepka.R
import ru.karpyzin.cepka.databinding.WidgetInappmessageBinding

fun Activity.inAppMessage(data: InAppMessage) {

    val rootView = window.findViewById<View>(android.R.id.content)
    val snackBar = Snackbar.make(rootView, "", Snackbar.LENGTH_LONG)
    val binding = WidgetInappmessageBinding.inflate(LayoutInflater.from(applicationContext))
    val snackBarLayout = snackBar.view as Snackbar.SnackbarLayout
    val layoutParams = snackBarLayout.layoutParams as FrameLayout.LayoutParams

    binding.inAppTitle.text = data.title
    binding.inAppTitle.isVisible = !data.title.isNullOrEmpty()
    binding.inAppSummary.text = data.summary
    binding.inAppSummary.setTextColor(ContextCompat.getColor(applicationContext, data.summaryColor))

    layoutParams.gravity = Gravity.TOP
    snackBar.setBackgroundTint(ContextCompat.getColor(applicationContext, R.color.transparent))
    snackBarLayout.elevation = 0f
    snackBarLayout.addView(binding.root)
    snackBarLayout.setPadding(0, 0, 0, 0)
    snackBarLayout.layoutParams = layoutParams

    snackBar.animationMode = Snackbar.ANIMATION_MODE_FADE
    snackBar.show()
}

data class InAppMessage(
    val title: String? = null,
    val summary: String?,
    @ColorRes val summaryColor: Int = R.color.text_color_hint
)
