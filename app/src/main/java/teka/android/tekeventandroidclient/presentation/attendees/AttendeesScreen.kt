package teka.android.tekeventandroidclient.presentation.attendees

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Divider
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationViewModel

@SuppressLint("FlowOperatorInvokedInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AttendeesScreen(){

    val guestRegistrationViewModel: GuestRegistrationViewModel = hiltViewModel();

    val visitorsState by guestRegistrationViewModel.allVisitors
        .flowOn(Dispatchers.Main)
        .collectAsState(initial = emptyList())

    Scaffold {
        VisitorsList(eventVisitors = visitorsState)
    }

}


@Composable
fun VisitorsList(eventVisitors: List<EventVisitor>) {

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        Text(
            text = "Visitors List",
            modifier = Modifier.padding(bottom = 8.dp),
            style = MaterialTheme.typography.h6
        )
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