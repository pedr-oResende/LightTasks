package br.com.lighttasks.presentation.screens.task.task_detail

import androidx.activity.OnBackPressedDispatcher
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Done
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.lighttasks.commom.extensions.getArgument
import br.com.lighttasks.commom.util.date.DateUtils
import br.com.lighttasks.commom.util.priority.getPriorityContainerColor
import br.com.lighttasks.domain.model.Task
import br.com.lighttasks.presentation.compose.animation.ShimmerTextItem
import br.com.lighttasks.presentation.compose.animation.shimmerBrush
import br.com.lighttasks.presentation.compose.components.dialog.DefaultAlertDialog
import br.com.lighttasks.presentation.compose.components.dialog.LoadingDialog
import br.com.lighttasks.presentation.compose.components.state.error.DefaultErrorMessage
import br.com.lighttasks.presentation.compose.navigation.Screens
import br.com.lighttasks.presentation.compose.widgets.top_bar.TopBar
import br.com.lighttasks.presentation.model.StateUI
import org.koin.androidx.compose.getViewModel
import org.koin.core.parameter.parametersOf

@Composable
fun TaskDetailMainScreen(
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel = getViewModel {
        parametersOf(
            navHostController.previousBackStackEntry?.savedStateHandle?.getArgument<Task>(
                key = Screens.TaskDetail.argumentKey
            )
        )
    },
    onBackPressedDispatcher: OnBackPressedDispatcher
) {
    var isLoading by remember { mutableStateOf(false) }
    TaskDetailScreen(
        navHostController = navHostController,
        viewModel = viewModel,
        onBackPressedDispatcher = onBackPressedDispatcher,
        isLoading = isLoading
    )
    viewModel.responsibleResponse.collectAsState().value.let { responsibleResponse ->
        when (responsibleResponse) {
            is StateUI.Processing -> {
                isLoading = true
            }
            is StateUI.Error -> {
                isLoading = false
                DefaultErrorMessage(message = "Ocorreu um erro inesperado")
            }
            is StateUI.Processed -> {
                isLoading = false
            }
            is StateUI.Idle -> Unit
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TaskDetailScreen(
    navHostController: NavHostController,
    viewModel: TaskDetailViewModel,
    onBackPressedDispatcher: OnBackPressedDispatcher,
    isLoading: Boolean
) {
    val taskDetailUI = viewModel.taskDetailUI.value
    val (showDialog, setShowDialog) = remember { mutableStateOf(false) }
    var showLoadingDialog by remember { mutableStateOf(false) }
    Scaffold(
        topBar = {
            TopBar(
                title = "",
                onBackPressed = {
                    onBackPressedDispatcher.onBackPressed()
                },
                actions = {
                    IconButton(onClick = {
                        val screen = if (taskDetailUI.task?.teamId == null)
                            Screens.CreatePersonalTask
                        else
                            Screens.CreateTaskForTeamMember
                        screen.navigateWithArgument(
                            navHostController = navHostController,
                            argumentValue = taskDetailUI.task
                        )
                    }) {
                        Icon(
                            imageVector = Icons.Default.Edit,
                            contentDescription = null
                        )
                    }
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {},
                floatingActionButton = {
                    FloatingActionButton(onClick = {
                        setShowDialog(true)
                    }) {
                        Icon(imageVector = Icons.Default.Done, contentDescription = null)
                    }
                })
        }
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues = paddingValues)
                .fillMaxSize()
                .padding(all = 16.dp)
        ) {
            DefaultAlertDialog(
                title = "Finalizar tarefa?",
                text = "Ao fazer isto, você estará finalzando esta tarefa em andamento.",
                buttonText = "Finalizar",
                onClick = {
                    viewModel.finishTask()
                },
                showDialog = showDialog,
                setShowDialog = setShowDialog
            )
            LoadingDialog(showDialog = showLoadingDialog, text = "Carregando")
            Text(
                text = taskDetailUI.task?.name.orEmpty(),
                style = MaterialTheme.typography.headlineSmall
            )
            Spacer(modifier = Modifier.height(32.dp))
            DescriptionMenuItem(
                title = "Descrição",
                text = taskDetailUI.task?.description.orEmpty()
            )
            Divider(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 16.dp)
            )
            DescriptionMenuItem(
                title = "Responsável",
                text = taskDetailUI.responsible?.fullName.orEmpty(),
                leadingIcon = {
                    Icon(imageVector = Icons.Default.AccountCircle, contentDescription = null)
                },
                isLoading = isLoading
            )
            Spacer(modifier = Modifier.height(16.dp))
            DescriptionMenuItem(
                title = "Prazo",
                text = DateUtils.getClientPatternDate(taskDetailUI.task?.deadline),
                leadingIcon = {
                    Box(
                        modifier = Modifier
                            .width(8.dp)
                            .fillMaxHeight()
                            .background(
                                color = getPriorityContainerColor(priority = taskDetailUI.task?.priority),
                                shape = RoundedCornerShape(50)
                            )
                    )
                }
            )
            viewModel.finishTaskResponse.collectAsState().value.let { response ->
                when (response) {
                    is StateUI.Error -> {
                        showLoadingDialog = false
                    }
                    is StateUI.Idle -> Unit
                    is StateUI.Processed -> {
                        showLoadingDialog = false
                        Screens.Tasks.backToScreen(navHostController)
                    }
                    is StateUI.Processing -> {
                        showLoadingDialog = true
                    }
                }
            }
        }
    }
}

@Composable
fun DescriptionMenuItem(
    title: String,
    text: String,
    leadingIcon: @Composable (() -> Unit)? = null,
    isLoading: Boolean = false
) {
    Text(
        text = title,
        style = MaterialTheme.typography.labelMedium,
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.7f)
    )
    Spacer(modifier = Modifier.height(8.dp))
    Row(modifier = Modifier.height(IntrinsicSize.Min)) {
        if (leadingIcon != null) {
            leadingIcon()
            Spacer(modifier = Modifier.width(8.dp))
        }
        if (isLoading) {
            ShimmerTextItem(brush = shimmerBrush())
        } else {
            Text(
                text = text,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
        }
    }
}