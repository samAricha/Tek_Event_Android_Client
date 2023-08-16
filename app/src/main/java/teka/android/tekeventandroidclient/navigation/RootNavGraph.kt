package teka.android.tekeventandroidclient.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import teka.android.tekeventandroidclient.MainAppScreen
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.presentation.auth.login.LoginScreen

@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = To_MAIN_GRAPH_ROUTE
) {
    NavHost(navController = navController,
        startDestination = startDestination,
        route = ROOT_GRAPH_ROUTE
    ){


        authNavGraph(navController = navController)


        composable(route = To_MAIN_GRAPH_ROUTE){
            val authViewModel: AuthViewModel = hiltViewModel()
            val isLoggedInState = authViewModel.isLoggedIn.collectAsState()
            if(!isLoggedInState.value){
                LoginScreen(navController)
            }else{
                MainAppScreen()
            }

        }

    }
}