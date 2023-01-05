package br.com.lighttasks.presentation.screens.task.create_task

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CalendarToday
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.util.date.DateUtils
import br.com.lighttasks.presentation.compose.widgets.date_picker.DefaultDatePicker
import br.com.lighttasks.presentation.compose.widgets.edit_text.FormEditText
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import br.com.lighttasks.presentation.screens.task.create_task.ui.CreateTaskEvents
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
        val (showDatePicker, setShowDatePicker) = rememberSaveable { mutableStateOf(false) }
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
            FormEditText(
                value = DateUtils.getClientPatternDate(createTaskUI.deadline),
                label = "Prazo",
                onValueChange = { },
                maxLines = 1,
                trailingIcon = {
                    IconButton(onClick = {
                        setShowDatePicker(true)
                    }) {
                        Icon(imageVector = Icons.Default.CalendarToday, contentDescription = null)
                    }
                }
            )
            DefaultDatePicker(
                startDate = DateUtils.getLocalDate(createTaskUI.deadline),
                showDatePicker = showDatePicker,
                setShowDatePicker = setShowDatePicker
            ) { date ->
                viewModel.onEvent(CreateTaskEvents.TaskDeadlineChanged(date))
            }
        }
    }
}