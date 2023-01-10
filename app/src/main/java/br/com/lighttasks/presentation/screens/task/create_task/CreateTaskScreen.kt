package br.com.lighttasks.presentation.screens.task.create_task

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.extensions.ifNull
import br.com.lighttasks.commom.extensions.noRippleClickable
import br.com.lighttasks.commom.util.date.DateUtils
import br.com.lighttasks.presentation.compose.widgets.date_picker.DefaultDatePicker
import br.com.lighttasks.presentation.compose.widgets.edit_text.FormEditText
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import br.com.lighttasks.presentation.screens.task.create_task.ui.CreateTaskEvents
import java.time.LocalDate
import java.util.*

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CreateTaskScreen(
    isTaskForTeamMember: Boolean,
    viewModel: CreateTaskViewModel,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    navHostController: NavHostController
) {
    val createTaskUI = viewModel.createTaskUI.value
    Scaffold(
        topBar = {
            TopBar(
                title = "",
                onBackPressed = {
                    onBackPressedDispatcher.onBackPressed()
                }
            )
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(all = 16.dp),
            verticalArrangement = Arrangement.spacedBy(18.dp)
        ) {
            FormEditText(
                value = createTaskUI.name,
                label = "Nome",
                onValueChange = {
                    viewModel.onEvent(CreateTaskEvents.TaskNameChanged(it))
                },
                maxLines = 1
            )
            FormEditText(
                value = createTaskUI.description,
                label = "Descrição",
                onValueChange = {
                    viewModel.onEvent(CreateTaskEvents.TaskDescriptionChanged(it))
                },
                maxLines = 3
            )
            DatePickerEditText(
                value = createTaskUI.deadline,
                label = "Prazo",
                onDateChanged = { date ->
                    viewModel.onEvent(CreateTaskEvents.TaskDeadlineChanged(date))
                }
            )
        }
    }
}

@Composable
fun DatePickerEditText(
    label: String,
    value: String,
    onDateChanged: (date: String) -> Unit
) {
    val (showDatePicker, setShowDatePicker) = rememberSaveable { mutableStateOf(false) }
    val deadline = if (value.isBlank())
        value
    else
        DateUtils.getClientPatternDate(value ifNull "")
    val startDate = if (value.isBlank()) LocalDate.now() else DateUtils.getLocalDate(value)
    FormEditText(
        modifier = Modifier.noRippleClickable { setShowDatePicker(true) },
        value = deadline,
        label = label,
        onValueChange = { },
        maxLines = 1,
        trailingIcon = {
            Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
        },
        enabled = false,
        useDisableColors = false
    )
    DefaultDatePicker(
        startDate = startDate,
        showDatePicker = showDatePicker,
        setShowDatePicker = setShowDatePicker
    ) { date ->
        onDateChanged(date)
    }
}