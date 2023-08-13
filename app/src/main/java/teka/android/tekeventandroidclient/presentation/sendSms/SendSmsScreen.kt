package teka.android.tekeventandroidclient.presentation.sendSms

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
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
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.ui.theme.Poppins
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.Shapes
import teka.android.tekeventandroidclient.ui.theme.buttonShapes
import teka.android.tekeventandroidclient.ui.theme.greenColor

@Composable
fun SendSmsScreen(
    navController: NavHostController,
) {
//    val context = LocalContext.current
    val viewModel: SmsViewModel = hiltViewModel()

    LazyColumn(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {

        item {

            Text(
                text = "Compose your Message",
                fontSize = 20.sp,
                fontWeight = FontWeight.Bold
            )

            Image(
                painter = painterResource(id = R.drawable.access),
                contentDescription = "Sms Logo",
                modifier = Modifier
                    .size(250.dp)
                    .padding(bottom = 16.dp),
                contentScale = ContentScale.Fit
            )

            Spacer(modifier = Modifier.height(20.dp))

            OutlinedTextField(
                value = viewModel.message,
                onValueChange = {
                    viewModel.message = it
                },
                label = {
                    Text(text = "Message", color = PrimaryColor)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 10.dp),
                colors = TextFieldDefaults.outlinedTextFieldColors(
                    unfocusedBorderColor = PrimaryColor,
                    textColor = PrimaryColor,
                    focusedBorderColor = PrimaryColor

                ),
                keyboardOptions = KeyboardOptions(
                    keyboardType =
                    KeyboardType.Text
                ),
                singleLine = false,
                shape = Shapes.large,
            )

            Spacer(modifier = Modifier.height(30.dp))

            Button(
                onClick = {
                    viewModel.sendMessage()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 20.dp)
                    .padding(top = 20.dp),
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = PrimaryColor,
                    contentColor = Color.White
                ),
                contentPadding = PaddingValues(vertical = 14.dp),
                shape = buttonShapes.large,
            ) {
                Text(text = "Send SMS", fontFamily = Poppins)

            }
        }
    }
}