package teka.android.tekeventandroidclient.navigation

import androidx.compose.animation.ExperimentalAnimationApi
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import com.google.accompanist.pager.ExperimentalPagerApi
import teka.android.tekeventandroidclient.presentation.auth.login.LoginScreen

@OptIn(ExperimentalAnimationApi::class, ExperimentalPagerApi::class)
fun NavGraphBuilder.authNavGraph(
    navController: NavHostController
){

    navigation(
        startDestination = Screen.LoginScreen.route,
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




    }
}