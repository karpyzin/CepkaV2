package ru.karpyzin.cepka.view.animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.DecelerateInterpolator
import androidx.core.view.marginBottom

class FloatingAnimation : Animation() {

    override fun togetherAnimations(view: View): List<ObjectAnimator> {
        val upSize = (view.width / 8).toFloat()
        val hideSize = view.marginBottom + view.height + upSize

        val moveY = ObjectAnimator.ofFloat(view, View.TRANSLATION_Y, 0f, -upSize, hideSize).apply {
            duration = 150
            interpolator = DecelerateInterpolator()
        }

        val byY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.1f, 0.8f).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
        }

        val byX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.1f, 0.8f).apply {
            duration = 200
            interpolator = DecelerateInterpolator()
        }

        return listOf(moveY, byY, byX)
    }
}