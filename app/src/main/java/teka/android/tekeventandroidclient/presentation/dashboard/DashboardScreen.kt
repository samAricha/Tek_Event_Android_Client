package teka.android.tekeventandroidclient.presentation.dashboard

import android.annotation.SuppressLint
import android.content.Context
import android.service.carrier.CarrierIdentifier
import android.widget.Toast
import androidx.compose.animation.core.animateDp
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
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
import androidx.compose.material.ripple.RippleAlpha
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import teka.android.tekeventandroidclient.R
import teka.android.tekeventandroidclient.data.room.models.EventVisitor
import teka.android.tekeventandroidclient.navigation.Screen
import teka.android.tekeventandroidclient.presentation.attendees.AttendeeViewModel
import teka.android.tekeventandroidclient.presentation.guestRegistration.GuestRegistrationViewModel
import teka.android.tekeventandroidclient.ui.components.SearchComposable
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.SecondaryColor

enum class MultiFloatingState{
    Expanded,
    Collapsed
}

enum class Identifier{
    Download,
    Upload,
    Analytics
}

class MiniFabItm(
    val icon: ImageBitmap,
    val label:String,
    val identifier: String
)

@SuppressLint("FlowOperatorInvokedInComposition", "UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DashboardScreen(){

    val context = LocalContext.current
    var multiFloatingState by remember {
        mutableStateOf(MultiFloatingState.Collapsed)
    }
    val items = listOf(
        MiniFabItm(
            icon = ImageBitmap.imageResource(id = R.drawable.cloud_download_bitmap),
            label = "Download",
            identifier = Identifier.Download.name,
        ),
        MiniFabItm(
            icon = ImageBitmap.imageResource(id = R.drawable.cloud_upload_bitmap),
            label = "Upload",
            identifier = Identifier.Upload.name
        ),
//        MiniFabItm(
//            icon = ImageBitmap.imageResource(id = R.drawable.data_analytics_bitmap),
//            label = "Analytics",
//            identifier = Identifier.Analytics.name
//        )

    )





    val guestRegistrationViewModel: GuestRegistrationViewModel = hiltViewModel();
    val dashboardViewModel: DashboardViewModel = hiltViewModel();

    val visitorsState by guestRegistrationViewModel.allVisitors
        .flowOn(Dispatchers.Main)
        .collectAsState(initial = emptyList())

    Scaffold(
        floatingActionButton = {

            MultiFloatingButton(
                multiFloatingState = multiFloatingState,
                onMultiFabStateChange = {
                    multiFloatingState = it
                },
                items = items,
                context = LocalContext.current
            )
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
                VisitorItem(
                    visitor = visitor
                )
            }
        }
    }
}

@Composable
fun VisitorItem(visitor: EventVisitor) {

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
            Icon(
                Icons.Default.Person,
                contentDescription = "Visitor Avatar",
                modifier = Modifier.size(30.dp),
            )

            Spacer(modifier = Modifier.width(8.dp))


            Column(
                verticalArrangement = Arrangement.Center
            ) {
                Text(text = "Name: ${visitor.first_name}")
                Text(text = "Phone: ${visitor.phone}")
            }

            if (visitor.attended) {
                IconButton(onClick = {
                    // Handle click action here if needed
                }) {
                    Icon(
                        Icons.Default.Check,
                        contentDescription = "Mark as Arrived",
                        tint = SecondaryColor
                    )
                }
            }
        }
    }
}


@Composable
fun MultiFloatingButton(
    multiFloatingState: MultiFloatingState,
    onMultiFabStateChange:(MultiFloatingState) -> Unit,
    context: Context,
    items:List<MiniFabItm>
){
    val dashboardViewModel: DashboardViewModel = hiltViewModel();

    val transition = updateTransition(targetState = multiFloatingState, label = "transition")
    
    val rotate by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 315f else 0f
    }

    val fabScale  by transition.animateFloat(label = "rotate") {
        if (it == MultiFloatingState.Expanded) 36f else 0f
    }

    val alpha by transition.animateFloat(
        label = "alpha",
        transitionSpec = { tween(durationMillis = 50) }
    ) {
        if (it == MultiFloatingState.Expanded) 1f else 0f
    }

    val textShadow by transition.animateDp(
        label = "textShadow",
        transitionSpec = { tween(durationMillis = 50) }
    ) {
        if (it == MultiFloatingState.Expanded) 2.dp else 0.dp
    }


    Column (
        horizontalAlignment = Alignment.End
    ){
        if(transition.currentState == MultiFloatingState.Expanded){
            items.forEach{
                MiniFab(
                    item = it,
                    onMiniFabItemClick = {
                            minFabItem ->
                                         when(minFabItem.identifier){
                                             Identifier.Download.name -> {
                                                 dashboardViewModel.getRemoteDataAndSaveLocally()
                                                Toast.makeText(context,"Downloading", Toast.LENGTH_SHORT).show()
                                             }
                                             Identifier.Upload.name -> {
                                                 dashboardViewModel.syncRoomDbToRemote()
                                                 Toast.makeText(context,"Uploading", Toast.LENGTH_SHORT).show()
                                             }
//                                             Identifier.Analytics.name -> {
//                                                 Toast.makeText(context,"Analytics", Toast.LENGTH_SHORT).show()
//                                             }
                                         }
                    },
                    alpha = alpha,
                    textShadow = textShadow,
                    fabScale = fabScale
                )
                Spacer(modifier = Modifier.size(16.dp))
            }

        }
        
        FloatingActionButton(
            onClick = {
                onMultiFabStateChange(
                    if (transition.currentState == MultiFloatingState.Expanded) {
                        MultiFloatingState.Collapsed
                    } else {
                        MultiFloatingState.Expanded
                    }
                )
            },
            backgroundColor = PrimaryColor
        ) {

            Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Download Contacts",
                    modifier = Modifier.size(28.dp).rotate(rotate),
                    tint = Color.White,
                )
        }
    }
}

@Composable
fun MiniFab(
    item: MiniFabItm,
    alpha: Float,
    textShadow: Dp,
    fabScale: Float,
    showLabel: Boolean = true,
    onMiniFabItemClick: (MiniFabItm) -> Unit
){
    val buttonColor = MaterialTheme.colors.secondary
    val shadow = Color.Black.copy(.5f)

    Row {
        if (showLabel) {
            Text(
                text = item.label,
                color = PrimaryColor,
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier
                    .alpha(
                        animateFloatAsState(
                            targetValue = alpha,
                            animationSpec = tween(50), label = ""
                        ).value
                    )
                    .shadow(textShadow)
                    .background(MaterialTheme.colors.surface)
                    .padding(start = 6.dp, end = 6.dp, top = 4.dp)
            )
            Spacer(modifier = Modifier.size(16.dp))
        }
        Canvas(
            modifier = Modifier
                .size(32.dp)
                .clickable(
                    interactionSource = MutableInteractionSource(),
                    onClick = {
                        onMiniFabItemClick.invoke(item)
                    },
                    indication = rememberRipple(
                        bounded = false,
                        radius = 20.dp,
                        color = MaterialTheme.colors.onSurface
                    )
                )
        ) {
            drawCircle(
                color = Color.White,
                radius = fabScale,
                center = Offset(
                    center.x + 2f,
                    center.y + 2f
                )

            )

            drawImage(
                image = item.icon,
                topLeft = Offset(
                    center.x - (item.icon.width / 2),
                    center.y - (item.icon.width / 2)
                ),
                colorFilter = ColorFilter.tint(PrimaryColor),
                alpha = alpha

            )
        }
    }
}


