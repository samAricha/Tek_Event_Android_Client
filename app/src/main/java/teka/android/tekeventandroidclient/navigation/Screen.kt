package teka.android.tekeventandroidclient.navigation

const val ROOT_GRAPH_ROUTE = "root_graph_route"
const val AUTH_GRAPH_ROUTE = "auth_graph_route"
const val To_MAIN_GRAPH_ROUTE = "to_main_graph_route"
const val MAIN_GRAPH_ROUTE = "main_graph_route"


sealed class Screen(val route: String) {
    object RegisterScreen : Screen(route = "register_screen")
    object LoginScreen : Screen(route = "login_screen")
    object WelcomeScreen : Screen(route = "welcome_screen")
    object AttendeeScreen : Screen(route = "attendee_screen")
    object SendSmsScreen: Screen(route = "sens_sms_screen")
    object GuestRegistrationScreen: Screen(route = "guest_registration_screen")
    object DashboardScreen: Screen(route = "dashboard_screen")
    object SettingsScreen: Screen(route = "settings_screen")
}