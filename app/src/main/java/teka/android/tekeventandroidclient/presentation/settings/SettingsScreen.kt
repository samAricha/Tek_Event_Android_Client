package teka.android.tekeventandroidclient.presentation.settings

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor

@Composable
fun SettingsScreen(
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.settingspng),
            contentDescription = "Settings image",
            modifier = Modifier
                .size(250.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

       Text(
                    text = "Page currently in development",
                    fontSize = 28.sp,
                    color = PrimaryColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )
    }


}