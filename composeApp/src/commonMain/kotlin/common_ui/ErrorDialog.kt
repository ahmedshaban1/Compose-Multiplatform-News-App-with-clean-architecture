package common_ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Button
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties

@Composable
fun ShowError(message: String, onActionClicked: () -> Unit) {
    Dialog(onDismissRequest = {}, properties = DialogProperties(
        usePlatformDefaultWidth = true
    )) {
        Card(modifier = Modifier.fillMaxWidth()) {
            Column(
                modifier = Modifier.padding(16.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = message, style = MaterialTheme.typography.h6)
                Spacer(modifier = Modifier.height(16.dp))
                Button(onClick = onActionClicked) {
                    Text(text = "Ok")
                }
            }
        }
    }
}