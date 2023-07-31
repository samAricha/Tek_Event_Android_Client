package teka.android.tekeventandroidclient.presentation.guestRegistration

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Shapes
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.presentation.sendSms.SmsViewModel
import teka.android.tekeventandroidclient.ui.theme.Poppins
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.Shapes

@SuppressLint("UnrememberedMutableState")
@Composable
fun GuestRegistrationScreen(navController: NavHostController) {

    val viewModel: GuestRegistrationViewModel = hiltViewModel()
    val guestNameState = mutableStateOf("")
    val phoneNumberState = mutableStateOf("")

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
//        verticalArrangement = Arrangement.Center
    ) {
        // Organization Logo
        Image(
            painter = painterResource(id = R.drawable.calendar),
            contentDescription = "Organization Logo",
            modifier = Modifier
                .size(150.dp)
                .padding(bottom = 16.dp, top = 16.dp),
            contentScale = ContentScale.Fit
        )

        Spacer(modifier = Modifier.height(30.dp))

//        TextField(
//            value = viewModel.guestName,
//            onValueChange = { viewModel.guestName = it },
//            modifier = Modifier
//                .fillMaxWidth()
//                .padding(bottom = 16.dp),
//            label = {
//                Text(text = "Guest Name")
//            },
//            textStyle = TextStyle(color = Color.Black),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text),
//            colors = TextFieldDefaults.textFieldColors(
//                cursorColor = Color.Black,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            singleLine = true
//        )




        OutlinedTextField(
            value = viewModel.guestName,
            onValueChange = {
                viewModel.guestName = it
            },
            label = {
                Text(text = "Guest Name", color = PrimaryColor)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = PrimaryColor,
                textColor = PrimaryColor

            ),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Text
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_person_24),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = Shapes.large,
        )

        Spacer(modifier = Modifier.height(20.dp))

//        TextField(
//            value = viewModel.phoneNumber,
//            onValueChange = { viewModel.phoneNumber = it },
//            modifier = Modifier.fillMaxWidth(),
//            label = {
//                Text(text = "Phone Number")
//            },
//            textStyle = TextStyle(color = Color.Black),
//            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Phone),
//            colors = TextFieldDefaults.textFieldColors(
//                cursorColor = Color.Black,
//                focusedIndicatorColor = Color.Transparent,
//                unfocusedIndicatorColor = Color.Transparent
//            ),
//            singleLine = true
//        )


        OutlinedTextField(
            value = viewModel.phoneNumber,
            onValueChange = {
                viewModel.phoneNumber = it
            },
            label = {
                Text(text = "Phone Number", color = PrimaryColor)
            },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp)
                .padding(top = 10.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                unfocusedBorderColor = PrimaryColor,
                textColor = PrimaryColor

            ),
            keyboardOptions = KeyboardOptions(
                keyboardType =
                KeyboardType.Phone
            ),
            singleLine = true,
            leadingIcon = {
                Icon(
                    painter = painterResource(id = R.drawable.baseline_phone_24),
                    contentDescription = "",
                    tint = PrimaryColor,
                    modifier = Modifier.size(24.dp)
                )
            },
            shape = Shapes.large,
        )

        Spacer(modifier = Modifier.height(20.dp))


//        Button(
//            onClick = { viewModel.saveGuest()
//                navController.navigate(Screen.AttendeeScreen.route)
//            }
//        ) {
//            Text(
//                text = "Save Guest",
//                modifier = Modifier.padding(10.dp),
//                color = Color.White,
//                fontSize = 15.sp
//            )
//        }


        Button(
            onClick = {
                viewModel.saveGuest()
                navController.navigate(Screen.AttendeeScreen.route)
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
            shape = Shapes.large,
        ) {
            Text(text = "Save Guest", fontFamily = Poppins)

        }
    }
}