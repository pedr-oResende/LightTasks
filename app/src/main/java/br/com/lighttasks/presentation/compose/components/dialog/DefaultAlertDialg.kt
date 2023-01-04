package br.com.lighttasks.presentation.compose.components.dialog

import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable

@Composable
fun DefaultAlertDialog(
    title: String,
    text: String,
    buttonText: String,
    onClick: () -> Unit,
    showDialog: Boolean,
    setShowDialog: (Boolean) -> Unit
) {
    if (showDialog) {
        AlertDialog(
            onDismissRequest = { setShowDialog(false) },
            title = { Text(text = title) },
            text = { Text(text = text) },
            confirmButton = {
                TextButton(onClick = {
                    setShowDialog(false)
                    onClick()
                }) {
                    Text(text = buttonText)
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    setShowDialog(false)
                }) {
                    Text(text = "Cancelar")
                }
            }
        )
    }
}