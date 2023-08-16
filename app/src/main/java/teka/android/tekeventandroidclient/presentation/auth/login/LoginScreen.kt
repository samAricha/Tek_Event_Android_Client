package teka.android.tekeventandroidclient.presentation.auth.login

import android.content.Context
import android.util.Log
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.LifecycleCoroutineScope
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.navigation.To_MAIN_GRAPH_ROUTE
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.presentation.auth.UserState
import teka.android.tekeventandroidclient.ui.theme.*
import kotlin.coroutines.coroutineContext
@Composable
fun LoginScreen(
    navController: NavController = rememberNavController(),
    authViewModel: AuthViewModel = hiltViewModel()
) {
    val mContext = LocalContext.current
    Log.d("lscrn", "inside login screen")
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var isPasswordOpen by remember { mutableStateOf(false) }
    val isLoggedInState = authViewModel.isLoggedIn.collectAsState()
    val vm = UserState.current.isLoggedIn.collectAsState()
//    val logInStatus = vm.value
    val logInStatus = true
    Log.d("LOGIN STATUS: ", "${vm.value}")

    @Composable
    fun myToast(){
        Toast.makeText(LocalContext.current, "state is $vm", Toast.LENGTH_LONG).show()
    }

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


    LazyColumn() {
        item {
        Box(
            contentAlignment = Alignment.BottomCenter,
            modifier = Modifier.padding(top = 10.dp)
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Text(
                    text = "Welcome to TekEvent",
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
                        .size(250.dp)
                        .padding(bottom = 6.dp),
                    contentScale = ContentScale.Fit
                )
                Spacer(modifier = Modifier.height(20.dp))
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(top = 6.dp),
                    backgroundColor = Color.White,
                    elevation = 0.dp,
                    shape = BottomBoxShape.medium
                ) {

                    Column(horizontalAlignment = Alignment.CenterHorizontally) {

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
                                Log.d("LOGIN STATUS: ", "${vm.value}")
                                Toast.makeText(mContext, "Feature coming soon ${vm.value}", Toast.LENGTH_SHORT)
                                    .show()


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
}








@Preview
@Composable
fun loginPreview(){
    LoginScreen()
}