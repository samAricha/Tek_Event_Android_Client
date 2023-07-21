package teka.android.tekeventandroidclient

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface

import androidx.compose.ui.Modifier
import dagger.hilt.android.AndroidEntryPoint
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationScreen
import teka.android.tekeventandroidclient.ui.theme.TekEventAndroidClientTheme

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TekEventAndroidClientTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
//                    GuestRegistrationScreen()
                    MainAppScreen()
                }
            }
        }
    }
}

