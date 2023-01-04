package br.com.lighttasks.presentation.compose.widgets.edit_text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FormEditText(
    modifier: Modifier = Modifier,
    label: String,
    value: String,
    onValueChange: (value: String) -> Unit,
    visualTransformation: VisualTransformation? = null,
    keyboardType: KeyboardType? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null,
    maxLines: Int = 1
) {
    OutlinedTextField(
        modifier = modifier.fillMaxWidth(),
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        label = {
            Text(text = label)
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ?: KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        shape = MaterialTheme.shapes.small,
        colors = TextFieldDefaults.outlinedTextFieldColors(),
        visualTransformation = visualTransformation ?: VisualTransformation.None,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon,
        maxLines = maxLines
    )
}