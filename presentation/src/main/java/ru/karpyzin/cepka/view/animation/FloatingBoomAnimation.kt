package ru.karpyzin.cepka.view.animation

import android.animation.ObjectAnimator
import android.view.View
import android.view.animation.LinearInterpolator

class FloatingBoomAnimation : Animation() {

    override fun togetherAnimations(view: View): List<ObjectAnimator> {

        val byY = ObjectAnimator.ofFloat(view, View.SCALE_Y, 1f, 1.4f, 1f).apply {
            duration = 100
            interpolator = LinearInterpolator()
        }

        val byX = ObjectAnimator.ofFloat(view, View.SCALE_X, 1f, 1.4f, 1f).apply {
            duration = 100
            interpolator = LinearInterpolator()
        }

        return listOf(byY, byX)
    }
}