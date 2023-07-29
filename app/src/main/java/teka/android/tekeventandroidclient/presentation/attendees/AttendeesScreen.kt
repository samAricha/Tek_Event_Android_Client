package teka.android.tekeventandroidclient.presentation.attendees

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Person
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.ui.components.SearchComposable

@SuppressLint("FlowOperatorInvokedInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun AttendeesScreen(){

    val attendeeViewModel: AttendeeViewModel = hiltViewModel();

    val filteredVisitorsState by attendeeViewModel.filteredVisitors
        .collectAsState(initial = emptyList())


    Scaffold(
        floatingActionButton = {
            FloatingActionButton(onClick = {
                // Define your action for adding a visitor here
                // For example, navigate to another composable/screen or show a dialog
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add Visitor")
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
            SearchComposable { query ->
                attendeeViewModel.onSearchQueryChanged(query)
            }

            VisitorsList(eventVisitors = filteredVisitorsState, attendeeViewModel)
        }
    }

}


@Composable
fun VisitorsList(eventVisitors: List<EventVisitor>, attendeeViewModel: AttendeeViewModel) {

    Column(
    ) {
        LazyColumn(

        ) {
            items(eventVisitors) { visitor ->
                VisitorItem(visitor = visitor, attendeeViewModel)
            }
        }
    }
}



@Composable
fun VisitorItem(visitor: EventVisitor, attendeeViewModel: AttendeeViewModel) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(top = 8.dp, bottom = 8.dp),
        elevation = 4.dp
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Visitor Avatar
            Icon(
                Icons.Default.Person,
                contentDescription = "Visitor Avatar",
                modifier = Modifier.size(30.dp),
                tint = if (visitor.attended) Color.Green else Color.Gray
            )

            Spacer(modifier = Modifier.width(8.dp))

            // Visitor Details
            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Name: ${visitor.first_name}")
                Text(text = "Phone: ${visitor.phone}")
            }

            IconButton(onClick = {
                attendeeViewModel.toggleArrivalStatus(visitor.id)
                // Handle database update or any other logic here
            }) {
                Icon(
                    Icons.Default.Check,
                    contentDescription = "Mark as Arrived",
                    tint = if (visitor.attended) Color.Green else Color.Gray
                )
            }
        }
    }
}