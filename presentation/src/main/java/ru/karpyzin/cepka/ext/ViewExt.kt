package ru.karpyzin.cepka.ext

import android.content.Context
import android.os.SystemClock
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.core.content.ContextCompat.getSystemService





/**
 * DebounceClickListener for double click protection
 */
fun View.setDebounceOnClickListener(listener: ((View) -> Unit)?) {
    setOnClickListener(listener?.let { DebounceViewClickListener(it) })
}

class SafeClickHandler(private val listener: ((View) -> Unit)) {
    companion object {
        private const val SAFE_CLICK_DELAY = 200
        var lastClickTime = 0L
        fun isCanHandleClick(currentTime: Long) = lastClickTime < (currentTime - (SAFE_CLICK_DELAY))
    }

    fun handleClick(v: View) {
        val currentTime = SystemClock.elapsedRealtime()
        if (isCanHandleClick(currentTime)) {
            listener(v)
            lastClickTime = currentTime
        }
    }
}

private class DebounceViewClickListener(clickListener: ((View) -> Unit)) : View.OnClickListener {
    private val clickHandler = SafeClickHandler(clickListener)
    override fun onClick(v: View) = clickHandler.handleClick(v)
}

/**
 * Keyboard
 */

fun View.showKeyboard() {
    val imm: InputMethodManager? = getSystemService(context, InputMethodManager::class.java)
    imm?.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}