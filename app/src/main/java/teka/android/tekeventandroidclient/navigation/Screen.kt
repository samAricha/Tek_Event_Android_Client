package teka.android.tekeventandroidclient.navigation

const val ROOT_GRAPH_ROUTE = "root_graph_route"
const val AUTH_GRAPH_ROUTE = "auth_graph_route"
const val MAIN_GRAPH_ROUTE = "main_graph_route"
const val To_MAIN_GRAPH_ROUTE = "to_main_graph_route"


sealed class Screen(val route: String) {
    object WelcomeScreen : Screen(route = "welcome_screen")
    object HomeScreen : Screen(route = "home_screen")
    object LoginScreen: Screen(route = "login_screen")
    object VisitorRegistrationScreen: Screen(route = "visitor_registration_screen")
    object DashboardScreen: Screen(route = "dashboard_screen")
}