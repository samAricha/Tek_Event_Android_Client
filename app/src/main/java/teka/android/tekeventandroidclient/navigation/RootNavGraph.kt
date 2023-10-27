package teka.android.tekeventandroidclient.navigation

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import kotlinx.coroutines.delay
import teka.android.tekeventandroidclient.MainAppScreen
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.presentation.auth.UserState
import teka.android.tekeventandroidclient.presentation.auth.login.LoginScreen
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor

@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun RootNavGraph(
    navController: NavHostController,
    startDestination: String = To_MAIN_GRAPH_ROUTE
) {
    NavHost(navController = navController,
//        startDestination = startDestination,
        startDestination = To_MAIN_GRAPH_ROUTE,
        route = ROOT_GRAPH_ROUTE
    ){


        authNavGraph(navController = navController)


        composable(route = To_MAIN_GRAPH_ROUTE){
            val vm = UserState.current
            val isLoggedInState by vm.isLoggedInState.collectAsState(initial = null)
            var isLoading by remember { mutableStateOf(true) }


            if (isLoggedInState != null) {
                if (isLoggedInState as Boolean) {
                    MainAppScreen()
                } else {
                    LoginScreen(navController)
//                    MainAppScreen()
                }
            } else {
                LaunchedEffect(isLoading) {
                    delay(30000) // Adjust the duration as needed (in milliseconds)
                    isLoading = false // Update the isLoading value
                }

                if (isLoading) {
                    ProgressIndicator()
                }
            }

        }

    }
}

@Composable
fun ProgressIndicator(){
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(color = PrimaryColor)
    }
}