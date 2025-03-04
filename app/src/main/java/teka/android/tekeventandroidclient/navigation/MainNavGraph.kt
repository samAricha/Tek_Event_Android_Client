package teka.android.tekeventandroidclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import teka.android.tekeventandroidclient.presentation.attendees.AttendeesScreen
import teka.android.tekeventandroidclient.presentation.dashboard.DashboardScreen
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationScreen
import teka.android.tekeventandroidclient.presentation.sendSms.SendSmsScreen
import teka.android.tekeventandroidclient.presentation.settings.SettingsScreen

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.AttendeeScreen.route,
        route = MAIN_GRAPH_ROUTE
    ) {

        composable(route = Screen.AttendeeScreen.route) {
            AttendeesScreen()
        }

        composable(route = Screen.GuestRegistrationScreen.route) {
            GuestRegistrationScreen(navController = navController)
        }

        composable(route = Screen.SendSmsScreen.route) {
            SendSmsScreen(navController = navController)
        }

        composable(route = Screen.DashboardScreen.route) {
            DashboardScreen()
        }

        composable(route = Screen.SettingsScreen.route) {
            SettingsScreen()
        }

    }
}