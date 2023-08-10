package teka.android.tekeventandroidclient.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import teka.android.tekeventandroidclient.authentication.models.RegisterRequest
import teka.android.tekeventandroidclient.presentation.auth.login.LoginScreen
import teka.android.tekeventandroidclient.presentation.auth.registration.RegisterScreen
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationScreen

fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){
    navigation(
        startDestination = Screen.RegisterScreen.route,
        route = AUTH_GRAPH_ROUTE
    ){


        composable(
            route = Screen.LoginScreen.route
        ) {
            LoginScreen(
                onClick = {
                    navController.popBackStack()
                    navController.navigate(To_MAIN_GRAPH_ROUTE)
                }
            )
        }

        composable(
            route = Screen.RegisterScreen.route
        ) {
            RegisterScreen(

            )
        }

    }
}