package teka.android.tekeventandroidclient.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost

@Composable
fun MainNavGraph(
    navController: NavHostController,
) {

    NavHost(
        navController = navController,
        startDestination = Screen.DashboardScreen.route,
        route = MAIN_GRAPH_ROUTE
    ) {

    }
}