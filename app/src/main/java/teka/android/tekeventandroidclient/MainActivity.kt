package teka.android.tekeventandroidclient

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.runtime.LaunchedEffect

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.core.content.ContextCompat
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationScreen
import teka.android.tekeventandroidclient.presentation.splashScreen.SplashViewModel
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.PrimaryVariant
import teka.android.tekeventandroidclient.ui.theme.TekEventAndroidClientTheme
import teka.android.tekeventandroidclient.ui.theme.greenColor
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @Inject
    lateinit var splashViewModel: SplashViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        window.statusBarColor = PrimaryVariant.toArgb()

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S){
            val splashScreen = installSplashScreen()
            splashScreen.setKeepOnScreenCondition { true }
        }


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

