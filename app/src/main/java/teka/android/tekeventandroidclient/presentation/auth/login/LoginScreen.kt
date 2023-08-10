package teka.android.tekeventandroidclient.presentation.auth.login

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
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
import androidx.navigation.NavController
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.navigation.To_MAIN_GRAPH_ROUTE
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.ui.theme.*

@Composable
fun LoginScreen(
    navController: NavController,
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    Log.d("lscrn", "inside login screen")
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }
    val isLoggedInState = authViewModel.isLoggedIn.collectAsState()



    // Automatically navigate when registration status changes
    LaunchedEffect(key1 = Unit) {
        val job = authViewModel.isLoggedIn.collect { isLoggedIn ->
            if (isLoggedIn) {
                navController.navigate(To_MAIN_GRAPH_ROUTE)
            }
        }

//        onDispose {
//            job.cancel()
//        }
    }


    Column() {

        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                modifier = Modifier
                    .padding(top = 100.dp),
            ) {
                Text(
                    text = "Welcome to TekEvent",
                    fontSize = 28.sp,
                    color = PrimaryColor,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 30.dp),
                    textAlign = TextAlign.Center,
                    fontWeight = FontWeight.Bold,
                )

                Button(
                    onClick = { },
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ),
                    contentPadding = PaddingValues(horizontal = 20.dp, vertical = 10.dp),
                    modifier = Modifier.padding(top = 20.dp),
                    shape = Shapes.large
                ) {
                    Row(verticalAlignment = Alignment.CenterVertically) {
                        Icon(
                            painter = painterResource(id = R.drawable.ic_google),
                            contentDescription = "",
                            tint = Color.Unspecified,
                            modifier = Modifier.size(26.dp)
                        )
                        Spacer(modifier = Modifier.width(20.dp))
                        Text(text = "Continue with Google", color = PrimaryColor, fontSize = 16.sp)
                    }
                }


                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 20.dp),
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    shape = BottomBoxShape.medium
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        Text(
                            text = "Log In with Email",
//                            fontFamily = Poppins,
                            fontSize = 12.sp,
                            modifier = Modifier.padding(top = 16.dp)
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
                            colors = TextFieldDefaults.outlinedTextFieldColors(
                                unfocusedBorderColor = PrimaryColor,
                                textColor = PrimaryColor

                            ),
                            keyboardOptions = KeyboardOptions(
                                keyboardType =
                                KeyboardType.Email
                            ),
                            singleLine = true,
                            leadingIcon = {
                                Icon(
                                    painter = painterResource(id = R.drawable.ic_email),
                                    contentDescription = "",
                                    tint = PrimaryColor,
                                    modifier = Modifier.size(24.dp)
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

                        Button(
//                            Log.d("TAG2", splashViewModel.isLoading.value.toString())
                            onClick = {
                                authViewModel.login(email, password)
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
                            Text(text = "Login", fontFamily = Poppins)

                        }



                        TextButton(
                            onClick = {
                                Toast.makeText(mContext, "Feature coming soon", Toast.LENGTH_SHORT).show()

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
                                      navController.navigate(route = Screen.RegisterScreen.route)
                            },
                            contentPadding = PaddingValues(vertical = 0.dp)
                        ) {
                            Text(
                                text = "Don't have an Account ? Sign Up",
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
}