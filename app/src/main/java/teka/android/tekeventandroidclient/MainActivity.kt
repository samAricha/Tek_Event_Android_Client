package teka.android.tekeventandroidclient

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import com.google.accompanist.pager.ExperimentalPagerApi
import dagger.hilt.android.AndroidEntryPoint
import teka.android.tekeventandroidclient.presentation.splashScreen.SplashViewModel
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.TekEventAndroidClientTheme
import javax.inject.Inject

@SuppressLint("CustomSplashScreen")
@ExperimentalAnimationApi
@ExperimentalPagerApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        window.statusBarColor = PrimaryColor.toArgb()
        val splashScreen = installSplashScreen()
        super.onCreate(savedInstanceState)

        splashScreen.setKeepOnScreenCondition{splashViewModel.isLoading.value}



        setContent {
            TekEventAndroidClientTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    MainAppScreen()
                }
            }
        }
    }
}

