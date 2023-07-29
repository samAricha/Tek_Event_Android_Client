package teka.android.tekeventandroidclient.presentation.dashboard

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationViewModel
import teka.android.tekeventandroidclient.ui.components.SearchComposable

@SuppressLint("FlowOperatorInvokedInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(){

    val guestRegistrationViewModel: GuestRegistrationViewModel = hiltViewModel();
    val dashboardViewModel: DashboardViewModel = hiltViewModel();

    val visitorsState by guestRegistrationViewModel.allVisitors
        .flowOn(Dispatchers.Main)
        .collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                dashboardViewModel.getRemoteDataAndSaveLocally()
            }) {
                Icon(
                    painter = painterResource(id = R.drawable.cloud_download),
                    contentDescription = "Download Contacts"
                )
            }
        }

    ) {
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            Text(
                text = "Visitors List",
                modifier = Modifier.padding(bottom = 8.dp),
                style = MaterialTheme.typography.h6
            )

            VisitorsList(eventVisitors = visitorsState)
        }
    }

}


@Composable
fun VisitorsList(eventVisitors: List<EventVisitor>) {

    Column(
    ) {
        LazyColumn(
            contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp)

        ) {
            items(eventVisitors) { visitor ->
                Text(text = "Name: ${visitor.first_name}")
                Text(text = "Phone: ${visitor.phone}")
                Divider()
            }
        }
    }
}