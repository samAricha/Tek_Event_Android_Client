package teka.android.tekeventandroidclient.ui.components

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Icon
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.hilt.navigation.compose.hiltViewModel
import teka.android.tekeventandroidclient.presentation.auth.AuthViewModel
import teka.android.tekeventandroidclient.ui.theme.PrimaryColor
import teka.android.tekeventandroidclient.ui.theme.ReemKufi

@Composable
fun CustomDialog(value: String, setShowDialog: (Boolean) -> Unit, setValue: (String) -> Unit) {

    val txtFieldError = remember { mutableStateOf("") }
    val txtField = remember { mutableStateOf(value) }
    val negativeButtonColor: Color = Color(0xFF35898F)
    val positiveButtonColor: Color = PrimaryColor
    val spaceBetweenElements: Dp = 18.dp
    val context: Context = LocalContext.current.applicationContext
    val authViewModel: AuthViewModel = hiltViewModel()

    Dialog(onDismissRequest = { setShowDialog(false) }) {
        Surface(
            modifier = Modifier.fillMaxWidth(0.92f),
            color = Color.Transparent // dialog background
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
            ) {

                // text and buttons
                Column(
                    modifier = Modifier
                        .padding(top = 30.dp) // this is the empty space at the top
                        .fillMaxWidth()
                        .background(
                            color = Color.White,
                            shape = RoundedCornerShape(percent = 10)
                        ),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Spacer(modifier = Modifier.height(height = 36.dp))

                    Text(
                        text = "LogOut?",
                        fontFamily = ReemKufi,
                        fontSize = 24.sp
                    )

                    Spacer(modifier = Modifier.height(height = spaceBetweenElements))

                    Text(
                        modifier = Modifier.padding(horizontal = 16.dp),
                        text = "Are you sure, you want to LogOut?",
                        fontFamily = ReemKufi,
                        fontSize = 18.sp
                    )

                    Spacer(modifier = Modifier.height(height = spaceBetweenElements))

                    // buttons
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceAround
                    ) {
                        DialogButton(
                            buttonColor = negativeButtonColor,
                            cornerRadiusPercent = 40,
                            buttonText = "No") {
                            Toast
                                .makeText(context, "No Click", Toast.LENGTH_SHORT)
                                .show()
                            setShowDialog(false)
                        }
                        DialogButton(
                            buttonColor = positiveButtonColor,
                            cornerRadiusPercent = 40,
                            buttonText = "Yes"
                        ) {
                            Toast
                                .makeText(context, "Logging Out", Toast.LENGTH_SHORT)
                                .show()
                            authViewModel.logout()
                            setShowDialog(false)
                        }
                    }

                    // If you decrease the Surface's width, increase this height
                    Spacer(modifier = Modifier.height(height = spaceBetweenElements * 2))
                }

                // delete icon
                Icon(
                    Icons.Rounded.Logout,
                    contentDescription = "Delete Icon",
                    tint = positiveButtonColor,
                    modifier = Modifier
                        .background(color = Color.White, shape = CircleShape)
                        .border(width = 2.dp, shape = CircleShape, color = positiveButtonColor)
                        .padding(all = 16.dp)
                        .align(alignment = Alignment.TopCenter)
                )
            }
        }
    }
}


@Composable
fun DialogButton(
    cornerRadiusPercent: Int = 26,
    buttonColor: Color,
    buttonText: String,
    onDismiss: () -> Unit
) {
    Box(
        modifier = Modifier
            .background(
                color = buttonColor,
                shape = RoundedCornerShape(percent = cornerRadiusPercent)
            )
            .clickable {
                onDismiss()
            }
            .padding(horizontal = 16.dp, vertical = 6.dp)
    ) {
        Text(
            text = buttonText,
            color = Color.White,
            fontSize = 18.sp,
            fontFamily = ReemKufi
        )
    }
}