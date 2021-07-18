package ru.karpyzin.cepka

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import dagger.hilt.android.AndroidEntryPoint
import ru.karpyzin.cepka.databinding.ActivityMainBinding
import ru.karpyzin.cepka.mvvm.home.HomeFragment
import ru.karpyzin.cepka.mvvm.subscriptions.SubscriptionsFragment
import ru.karpyzin.cepka.mvvm.week.WeekFragment
import ru.karpyzin.cepka.navigation.Screens
import ru.karpyzin.cepka.view.viewBinding
import ru.terrakok.cicerone.NavigatorHolder
import ru.terrakok.cicerone.android.support.SupportAppNavigator
import timber.log.Timber
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    @Inject
    lateinit var navigatorHolder: NavigatorHolder

    private val navigator = SupportAppNavigator(this, R.id.mainFrameContainer)
    private val viewModel: MainActivityViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        viewModel.init()

        binding.bottomNavigation.setOnNavigationItemSelectedListener {
            val screen = when (it.itemId) {
                R.id.navigation_home -> Screens.HomeFragmentScreen
                R.id.navigation_week -> Screens.WeekFragmentScreen
                R.id.navigation_subscriptions -> Screens.SubscriptionsFragmentScreen
                else -> Screens.HomeFragmentScreen
            }
            viewModel.changeMainScreen(screen)
            true
        }

        binding.bottomNavigation.selectedItemId = R.id.navigation_home
    }

    override fun onResumeFragments() {
        super.onResumeFragments()
        navigatorHolder.setNavigator(navigator)
    }

    override fun onPause() {
        super.onPause()
        navigatorHolder.removeNavigator()
    }
}