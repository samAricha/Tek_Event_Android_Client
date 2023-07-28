package teka.android.tekeventandroidclient

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import teka.android.tekeventandroidclient.navigation.MainNavGraph
import teka.android.tekeventandroidclient.navigation.Screen

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MainAppScreen() {
    val navHostController: NavHostController = rememberNavController()


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
                })
        },

        bottomBar = {
            BottomNavigation {
                val navBackStackEntry by navHostController.currentBackStackEntryAsState()
                val currentRoute = navBackStackEntry?.destination?.route

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
                            contentDescription = "Home"
                        )
                    },
                    label = {
                        Text(text = "Home")
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
                            contentDescription = "Record"
                        )
                    },
                    label = {
                        Text(text = "Record")
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
                            contentDescription = "SMS"
                        )
                    },
                    label = {
                        Text(text = "SMS")
                    }
                )
                BottomNavigationItem(
                    selected = currentRoute?.startsWith(Screen.DashboardScreen.route) == true,
                    onClick = {
                        navHostController.navigate(route = Screen.DashboardScreen.route)
                    },
                    icon = {
                        Icon(
                            painter = painterResource(R.drawable.dashboard),
                            contentDescription = "Dashboard"
                        )
                    },
                    label = {
                        Text(text = "Dashboard")
                    }
                )
            }
        }
        ) {
            Box(modifier = Modifier.padding(bottom = 60.dp)) {
                MainNavGraph(navController = navHostController)
            }
        }
}













//to be used in refactoring the code
data class BottomNavigationItem(
    val route: String,
    val icon: Int,
    val contentDescription: String,
    val label: String
)

val bottomNavigationItems = listOf(
    BottomNavigationItem(
        route = Screen.DashboardScreen.route,
        icon = R.drawable.dashboard,
        contentDescription = "Send SMS",
        label = "Send SMS"
    ),
    BottomNavigationItem(
        route = Screen.DashboardScreen.route,
        icon = R.drawable.dashboard,
        contentDescription = "Add Contact List",
        label = "Recipients"
    ),
    BottomNavigationItem(
        route = Screen.DashboardScreen.route,
        icon = R.drawable.dashboard,
        contentDescription = "Contact List",
        label = "Other Screen"
    )
)