package br.com.lighttasks.presentation.compose.widgets.edit_text

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import br.com.lighttasks.commom.extensions.ifNull

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomEditText(
    modifier: Modifier = Modifier,
    placeholder: String? = null,
    value: String,
    backgroundColor: Color = MaterialTheme.colorScheme.surfaceVariant,
    onValueChange: (value: String) -> Unit,
    visualTransformation: VisualTransformation? = null,
    keyboardType: KeyboardType? = null,
    isError: Boolean = false,
    leadingIcon: @Composable (() -> Unit)? = null,
    trailingIcon: @Composable (() -> Unit)? = null
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .height(52.dp)
            .clip(RoundedCornerShape(50)),
        value = value,
        onValueChange = { newText ->
            onValueChange(newText)
        },
        placeholder = {
            if (placeholder != null) {
                Text(text = placeholder, fontWeight = FontWeight.Light)
            }
        },
        singleLine = true,
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType ifNull KeyboardType.Text,
            imeAction = ImeAction.Next
        ),
        colors = TextFieldDefaults.textFieldColors(
            focusedLabelColor = Color.Transparent,
            cursorColor = MaterialTheme.colorScheme.primary,
            containerColor = backgroundColor,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            textColor = contentColorFor(backgroundColor = backgroundColor)
        ),
        visualTransformation = visualTransformation ifNull VisualTransformation.None,
        isError = isError,
        leadingIcon = leadingIcon,
        trailingIcon = trailingIcon
    )
}