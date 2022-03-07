package ru.karpyzin.cepka.view.animation

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.view.View

/**
 * Creating animation for view
 * Supported:
 * - Direct animations
 * - Reverse animations
 * - Custom reverse animations
 * @author Danila Karpyzin at 11.11.2021
 */
abstract class Animation {

    companion object {
        const val DEFAULT_ANIMATION_DURATION = 300L
    }

    private var animSet: AnimatorSet? = null

    /**
     * Get animations for synchronous playback
     */
    open fun togetherAnimations(view: View): List<ObjectAnimator> = emptyList()

    /**
     * Get animations for sequential playback
     */
    open fun sequentiallyAnimations(view: View): List<ObjectAnimator> = emptyList()

    /**
     * You can override reverse default animations with custom
     */
    open fun reverseAnimation(view: View): List<ObjectAnimator>? = null

    /**
     * Run reverse animations. Current animations will be cancelled
     */
    open fun reverse(view: View) {
        if (animSet?.isRunning == true) {
            animSet?.cancel()
        }

        val customReverseAnimations = reverseAnimation(view)

        if (customReverseAnimations == null) {
            togetherAnimations(view).map {
                it.reverse()
            }
            animSet?.start()
        } else {
            animSet = null
            animSet = AnimatorSet().apply {
                playTogether(customReverseAnimations)
                start()
            }
        }
    }

    /**
     * Start direct animations. Current animations will be cancelled
     */
    fun animate(view: View) {
        if (animSet?.isRunning == true) {
            animSet?.cancel()
        }
        animSet = null

        animSet = AnimatorSet().apply {
            playTogether(togetherAnimations(view))
            playSequentially(sequentiallyAnimations(view))
            start()
        }

    }
}