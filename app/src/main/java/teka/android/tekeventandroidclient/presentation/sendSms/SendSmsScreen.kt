package teka.android.tekeventandroidclient.presentation.sendSms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.ui.theme.greenColor

@Composable
fun SendSmsScreen(
    navController: NavHostController,
) {
//    val context = LocalContext.current
    val viewModel: SmsViewModel = viewModel()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Compose your Message",
            color = greenColor,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold
        )

//        TextField(
//            value = viewModel.phoneNumber,
//            onValueChange = { viewModel.phoneNumber = it },
//            placeholder = { Text(text = "Enter your phone number") },
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
//            modifier = Modifier
//                .padding(16.dp)
//                .fillMaxWidth(),
//            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
//            singleLine = true
//        )


        // Organization Logo
        Image(
            painter = painterResource(id = R.drawable.sms),
            contentDescription = "Sms Logo",
            modifier = Modifier
                .size(200.dp)
                .padding(bottom = 16.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(20.dp))

        TextField(
            value = viewModel.message,
            onValueChange = { viewModel.message = it },
            placeholder = { Text(text = "Enter your message") },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            textStyle = TextStyle(color = Color.Black, fontSize = 15.sp),
            singleLine = false
        )

        Spacer(modifier = Modifier.height(20.dp))

        Button(
            onClick = { viewModel.sendMessage()
                navController.navigate(Screen.AttendeeScreen.route)}
        ) {
            Text(
                text = "Send SMS",
                modifier = Modifier.padding(10.dp),
                color = Color.White,
                fontSize = 15.sp
            )
        }
    }
}