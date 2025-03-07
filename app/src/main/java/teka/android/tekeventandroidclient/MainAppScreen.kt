package teka.android.tekeventandroidclient

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import teka.android.tekeventandroidclient.navigation.AUTH_GRAPH_ROUTE
import teka.android.tekeventandroidclient.navigation.MainNavGraph
import teka.android.tekeventandroidclient.navigation.RootNavGraph
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.navigation.authNavGraph
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.ui.theme.Poppins
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor


@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainAppScreen() {
    val navHostController: NavHostController = rememberNavController()

    val bottomNavigationTextStyle = TextStyle(
        fontFamily = Poppins,
        fontWeight = FontWeight.Light,
        fontSize = 10.sp
    )

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "TekEvent",
                        modifier = Modifier.fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        color = Color.White
                    )
                },
            )
        },

        bottomBar = {
            BottomNavigation(
                backgroundColor = Color.White,
                elevation = 16.dp
            ) {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

                fun getIconTint(selected: Boolean): Color = if (selected) {
                    PrimaryColor
                } else {
                    Color.Gray
                }

                BottomNavigationItem(
                    selected = currentRoute == Screen.AttendeeScreen.route,
                    onClick = {
                        navHostController.navigate(Screen.AttendeeScreen.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.home),
                            contentDescription = "Home",
                            tint = getIconTint(currentRoute == Screen.AttendeeScreen.route)
                        )
                    },
                    label = {
                        Text(
                            text = "Home",
                            color = getIconTint(currentRoute == Screen.AttendeeScreen.route),
                            style = bottomNavigationTextStyle
                        )
                    }
                )

                BottomNavigationItem(
                    selected = currentRoute == Screen.GuestRegistrationScreen.route,
                    onClick = {
                        navHostController.navigate(Screen.GuestRegistrationScreen.route) {
                            launchSingleTop = true
                        }
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.edit_note),
                            contentDescription = "Record",
                            tint = getIconTint(currentRoute == Screen.GuestRegistrationScreen.route)
                        )
                    },
                    label = {
                        Text(
                            text = "Record",
                            style = bottomNavigationTextStyle,
                            color = getIconTint(currentRoute == Screen.GuestRegistrationScreen.route)
                        )
                    }
                )
                BottomNavigationItem(
                    selected = currentRoute?.startsWith(Screen.SendSmsScreen.route) == true,
                    onClick = {
                        navHostController.navigate(route = Screen.SendSmsScreen.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_sms_24),
                            contentDescription = "SMS",
                            tint = getIconTint(currentRoute == Screen.SendSmsScreen.route)
                        )
                    },
                    label = {
                        Text(
                            text = "SMS",
                            style = bottomNavigationTextStyle,
                            color = getIconTint(currentRoute == Screen.SendSmsScreen.route)
                        )
                    }
                )
                BottomNavigationItem(
                    selected = currentRoute?.startsWith(Screen.DashboardScreen.route) == true,
                    onClick = {
                        navHostController.navigate(route = Screen.DashboardScreen.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.baseline_bar_chart_24),
                            contentDescription = "Dash",
                            tint = getIconTint(currentRoute == Screen.DashboardScreen.route)
                        )
                    },
                    label = {
                        Text(
                            text = "Stats",
                            style = bottomNavigationTextStyle,
                            color = getIconTint(currentRoute == Screen.DashboardScreen.route)
                        )

                    }
                )

                BottomNavigationItem(
                    selected = currentRoute?.startsWith(Screen.SettingsScreen.route) == true,
                    onClick = {
                        navHostController.navigate(route = Screen.SettingsScreen.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.settings),
                            contentDescription = "Settings",
                            tint = getIconTint(currentRoute == Screen.SettingsScreen.route)
                        )
                    },
                    label = {
                        Text(
                            text = "Settings",
                            style = bottomNavigationTextStyle,
                            color = getIconTint(currentRoute == Screen.SettingsScreen.route)
                        )

                    }
                )
            }
        }
        ) {
            Box(modifier = Modifier.padding(bottom = 60.dp)) {
                MainNavGraph(navController = navHostController)
//                val authViewModel: AuthViewModel = hiltViewModel()
//                val isLoggedInState = authViewModel.isLoggedIn.collectAsState()
//                if(!isLoggedInState.value){
//                    RootNavGraph(navController = navHostController, startDestination = AUTH_GRAPH_ROUTE)
//                }else{
//
//                }

            }
        }
}


@Composable
@Preview
fun MainAppScreenPreview() {
    // Preview your MainAppScreen Composable
    MainAppScreen()
}
