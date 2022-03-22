package ru.karpyzin.cepka

import android.os.Bundle
import android.view.HapticFeedbackConstants
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.lifecycle.lifecycleScope
import androidx.navigation.NavController
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import ru.karpyzin.cepka.base.BaseFragment
import ru.karpyzin.cepka.databinding.ActivityMainBinding
import ru.karpyzin.cepka.ext.collectWhenCreated
import ru.karpyzin.cepka.ext.setDebounceOnClickListener
import ru.karpyzin.cepka.notifications.PushManager
import ru.karpyzin.cepka.view.animation.FloatingAnimation
import ru.karpyzin.cepka.view.animation.FloatingBoomAnimation
import ru.karpyzin.cepka.view.widgets.inAppMessage
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var pushManager: PushManager

    private lateinit var navController: NavController

    private val viewModel: MainActivityViewModel by viewModels()

    private lateinit var binding: ActivityMainBinding

    private var mIsMainButtonEnabled: Boolean? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        lifecycleScope.launchWhenCreated {
            delay(1500)
            binding.mainActionButton.shrink()
        }

        pushManager.message.collectWhenCreated(lifecycleScope) {
            inAppMessage(it)
        }

        binding.mainActionButton.setDebounceOnClickListener { view ->
            supportFragmentManager.fragments.forEach { parent ->
                parent.childFragmentManager.fragments.forEach {
                    if (it is BaseFragment && it.isVisible) {
                        view.performHapticFeedback(HapticFeedbackConstants.CLOCK_TICK)
                        it.onMainButtonClicked()
                    }
                }
            }
        }

    }

    override fun onSupportNavigateUp(): Boolean {
        return navController.navigateUp()
    }

    fun updateMainButtonIcon(iconRes: Int?) = iconRes?.let {
        binding.mainActionButton.icon = AppCompatResources.getDrawable(applicationContext, it)
    }

    fun showMainButton(show: Boolean) {
        if (show == mIsMainButtonEnabled) {
            FloatingBoomAnimation().animate(binding.mainActionButton)
            return
        }

        mIsMainButtonEnabled = show
        lifecycleScope.launchWhenResumed {
            delay(100)
            if (show) {
                FloatingAnimation().reverse(binding.mainActionButton)
            } else {
                FloatingAnimation().animate(binding.mainActionButton)
            }
        }
    }
}