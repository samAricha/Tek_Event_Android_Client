package teka.android.tekeventandroidclient.presentation.auth.registration

import android.widget.Toast
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
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.navigation.To_MAIN_GRAPH_ROUTE
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.ui.theme.BottomBoxShape
import teka.android.tekeventandroidclient.ui.theme.Poppins
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.Shapes

@Composable
fun RegisterScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val localContext = LocalContext.current

    var phone by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var passwordConfirmation by remember { mutableStateOf("") }
    val isRegisteredState = authViewModel.isRegistered.collectAsState()
    var isPasswordOpen by remember { mutableStateOf(false) }
    var isPasswordConfirmationOpen by remember { mutableStateOf(false) }
    val mContext = LocalContext.current

    val registrationViewModel : RegistrationViewModel = hiltViewModel()


    val isEmailError = registrationViewModel.formState.emailError != null
    val emailErrorMessage = registrationViewModel.formState.emailError

    val isPhoneNumberError = registrationViewModel.formState.phoneNumberError != null
    val phoneNumberErrorMessage = registrationViewModel.formState.phoneNumberError

    val isPasswordError = registrationViewModel.formState.passwordError != null
    val passwordErrorMessage = registrationViewModel.formState.passwordError

    val isPasswordConfirmationError = registrationViewModel.formState.passwordConfirmationError != null
    val passwordConfirmationErrorMessage = registrationViewModel.formState.passwordConfirmationError



    // Automatically navigate when registration status changes
    LaunchedEffect(key1 = Unit) {
        val job = authViewModel.isRegistered.collect { isRegisterd ->
            if (isRegisterd) {
                navController.navigate(To_MAIN_GRAPH_ROUTE)
            }
        }
    }



    LazyColumn(
        modifier = Modifier
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        item {
            Card(
                modifier = Modifier
                    .fillMaxWidth(),
                backgroundColor = Color.White,
                elevation = 0.dp,
                shape = BottomBoxShape.medium
            ) {

                Column(horizontalAlignment = Alignment.CenterHorizontally) {

                    Text(
                        text = "Register to TekEvent",
                        fontSize = 18.sp,
                        color = PrimaryColor,
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(top = 10.dp),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                    )
                    Spacer(modifier = Modifier.height(20.dp))
                    Image(
                        painter = painterResource(id = R.drawable.access),
                        contentDescription = "Accept Svg",
                        modifier = Modifier
                            .size(200.dp),
                        contentScale = ContentScale.Fit
                    )
                    Spacer(modifier = Modifier.height(20.dp))

                    OutlinedTextField(
                        value = userName,
                        onValueChange = {
                            userName = it
                        },
                        label = {
                            Text(text = "Name", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType =
                            KeyboardType.Email
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_person_24),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = PrimaryColor
                            )
                        },
                        shape = Shapes.large,
                    )

                    OutlinedTextField(
                        value = phone,
                        onValueChange = {
                            phone = it
                            registrationViewModel.onEvent(MainEvent.PhoneNumberChanged(it))
                        },
                        label = {
                            Text(text = "Phone", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType =
                            KeyboardType.Phone
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.baseline_phone_24),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = PrimaryColor
                            )
                        },
                        shape = Shapes.large,
                    )

                    OutlinedTextField(
                        value = email,
                        onValueChange = {
                            email = it
                        },
                        label = {
                            Text(text = "Email Address", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        keyboardOptions = KeyboardOptions(
                            keyboardType =
                            KeyboardType.Email
                        ),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        singleLine = true,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_email),
                                contentDescription = "",
                                modifier = Modifier.size(24.dp),
                                tint = PrimaryColor
                            )
                        },
                        shape = Shapes.large,
                    )

                    OutlinedTextField(
                        value = password,
                        onValueChange = {
                            password = it
                        },
                        label = {
                            Text(text = "Password", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { isPasswordOpen = !isPasswordOpen }) {
                                if (!isPasswordOpen) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_eye_open),
                                        contentDescription = "",
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_eye_close),
                                        contentDescription = "",
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        },
                        shape = Shapes.large,
                    )

                    //password confirmation
                    OutlinedTextField(
                        value = passwordConfirmation,
                        onValueChange = {
                            passwordConfirmation = it
                        },
                        label = {
                            Text(text = "Confirm Password", color = PrimaryColor)
                        },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 20.dp)
                            .padding(top = 10.dp),
                        colors = TextFieldDefaults.outlinedTextFieldColors(
                            unfocusedBorderColor = PrimaryColor,
                            textColor = PrimaryColor
                        ),
                        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password),
                        singleLine = true,
                        visualTransformation = if (!isPasswordOpen) PasswordVisualTransformation() else VisualTransformation.None,
                        leadingIcon = {
                            Icon(
                                painter = painterResource(id = R.drawable.ic_password),
                                contentDescription = "",
                                tint = PrimaryColor,
                                modifier = Modifier.size(24.dp)
                            )
                        },
                        trailingIcon = {
                            IconButton(onClick = { isPasswordConfirmationOpen = !isPasswordConfirmationOpen }) {
                                if (!isPasswordConfirmationOpen) {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_eye_open),
                                        contentDescription = "",
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                } else {
                                    Icon(
                                        painter = painterResource(id = R.drawable.ic_eye_close),
                                        contentDescription = "",
                                        tint = PrimaryColor,
                                        modifier = Modifier.size(24.dp)
                                    )
                                }
                            }
                        },
                        shape = Shapes.large,
                    )

                    Button(
                        onClick = {
                            if (!isPhoneNumberError && !isEmailError && !isPasswordError && !isPasswordConfirmationError){
                                authViewModel.register(
                                    name = userName,
                                    email = email,
                                    password = password,
                                    passwordConfirmation = passwordConfirmation
                                )
                            }else{
                                Toast.makeText(localContext,"Please fix the errors", Toast.LENGTH_SHORT).show()
                            }


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
                        Text(text = "Register", fontFamily = Poppins)

                    }



                    TextButton(
                        onClick = {
                            Toast.makeText(mContext, "Feature coming soon", Toast.LENGTH_SHORT)
                                .show()

                        },
                        contentPadding = PaddingValues(vertical = 0.dp)
                    ) {
                        Text(
                            text = "Forgot Password ?",
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 26.dp)
                        )
                    }
                    TextButton(
                        onClick = {
                            navController.navigate(
                                route = Screen.LoginScreen.route
                            )
                                  },
                        contentPadding = PaddingValues(vertical = 0.dp)
                    ) {
                        Text(
                            text = "Already have an Account ? Log In",
                            fontFamily = Poppins,
                            fontSize = 12.sp,
                        )
                    }
                    Spacer(modifier = Modifier.height(20.dp))
                }
            }
        }
    }
}
