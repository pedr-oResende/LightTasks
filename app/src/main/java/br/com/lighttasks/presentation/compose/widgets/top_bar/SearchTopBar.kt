package br.com.lighttasks.presentation.compose.widgets.top_bar

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.runtime.*
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import br.com.lighttasks.presentation.compose.widgets.edit_text.SearchEditText

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchTopBar(
    searchText: String,
    placeholderText: String,
    onSearchTextChanged: (String) -> Unit,
    onClearClick: () -> Unit
) {
    val (showClearButton, setShowClearButton) = remember { mutableStateOf(false) }
    val keyboardController = LocalSoftwareKeyboardController.current
    val focusRequester = remember { FocusRequester() }
    TopBar(title = "") {
        SearchEditText(
            searchText = searchText,
            onSearchTextChanged = onSearchTextChanged,
            placeholder = placeholderText,
            showClearButton = showClearButton,
            setShowClearButton = setShowClearButton,
            keyboardController = keyboardController,
            focusRequester = focusRequester,
            onClearClick = onClearClick
        )
    }
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
    }
}