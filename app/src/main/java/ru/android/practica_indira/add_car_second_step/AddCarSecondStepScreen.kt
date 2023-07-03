package ru.android.practica_indira.add_car_second_step

import android.content.Context
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.outlined.DateRange
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DatePicker
import androidx.compose.material3.DatePickerDialog
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TextField
import androidx.compose.material3.rememberDatePickerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.LocalDate
import java.util.Calendar
import java.util.Date

enum class RepairTypeSelect(val type: String) {
    CHANGE_OF_OIL("Замена масла"),
    CAR_WIPERS("Дворники"),
    GLASS_REPLACEMENT("Замена стекла"),
    WIRING_REPLACEMENT("Замена проводки"),
    BATTERY_REPLACEMENT("Замена аккумулятора")
}

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AddCarSecondStepScreen(
    onNext: () -> Unit
) {
    Scaffold() { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top = innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            ),
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            item {
                Text(text = "Пробег и ремонт",
                    fontSize = 36.sp,
                    fontWeight = FontWeight(700),
                    modifier = Modifier.padding(top = 113.dp),
                )
            }
            item {
                Column(horizontalAlignment = Alignment.CenterHorizontally)
                {
                    Text(
                        text = "Текущий пробег и история ремонтов",
                        fontSize = 15.sp,
                        color = Color(0xCC1C1939),
                    )
                    Text(
                        text = "Шаг 2/2",
                        fontSize = 15.sp,
                        color = Color(0xCC1C1939),
                        modifier = Modifier.padding(top = 12.dp)
                    )
                }
            }
            item {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    modifier = Modifier
                        .padding(top = 48.dp)
                        .padding(horizontal = 37.dp),
                )
                {
                    Text(
                        text = "Текущий пробег",
                        fontSize = 24.sp,
                        color = Color(0xFF575476),
                        fontWeight = FontWeight(600)
                    )
                    Surface(
                        color = Color(0xFFF7F7F7),
                        shape = MaterialTheme.shapes.extraSmall,
                        modifier = Modifier.padding(top = 16.5.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.spacedBy(8.dp, Alignment.End)

                        ) {
                            Text(
                                text = "024558",
                                fontSize = 44.sp,
                                fontWeight = FontWeight(600),
                                letterSpacing = 2.sp,
                                color = Color(0xFF59576D),
                                textAlign = TextAlign.Right,
                                modifier = Modifier.padding(
                                    top = 9.dp,
                                    bottom = 12.dp,
                                    start = 16.dp
                                )
                            )
                            Text(
                                text = "км",
                                textAlign = TextAlign.Right,
                                fontSize = 20.sp,
                                color = Color(0xFF59576D),
                                modifier = Modifier.padding(end = 16.dp)
                            )
                        }
                    }
                    AddTypeRepair(
                        menuName = "Вид ремонта",
                        modifier = Modifier.padding(top = 114.dp)
                    )
                    MyDateDialog(modifier = Modifier.padding(top = 8.dp))
                    MileageSurface(modifier = Modifier.padding(top = 8.dp),
                        text = "Пробег", subtext = "км")

                }
            }

            item {
                Button(
                    onClick = onNext,
                    modifier = Modifier
                        .padding(horizontal = 37.dp)
                        .padding(top = 50.dp),
                    shape = MaterialTheme.shapes.extraSmall
                ) {
                    Text(
                        text = "Готово",
                        modifier = Modifier
                            .padding(horizontal = 100.dp, vertical = 10.dp)
                    )
                }
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddTypeRepair(
    menuName: String,
    modifier: Modifier = Modifier
) {

    var isExpanded by remember {
        mutableStateOf(false)
    }

    var item by remember {
        mutableStateOf("")
    }

    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        ExposedDropdownMenuBox(
            expanded = isExpanded,
            onExpandedChange = { isExpanded = it }
        ) {
            Surface(
                modifier = Modifier.fillMaxWidth(),
                color = Color(0xFFF7F7F7),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = item.takeIf {
                            it.isNotEmpty()
                        } ?: menuName,

                        modifier = Modifier
                            .padding(16.dp)
                            .menuAnchor()
                    )
                    IconButton(onClick = { isExpanded = true }) {
                        Icon(Icons.Default.ArrowDropDown, contentDescription = null,)
                    }

                }
            }
            ExposedDropdownMenu(
                expanded = isExpanded,
                onDismissRequest = { isExpanded = false },
                modifier = Modifier.fillMaxWidth()
            ) {
                DropdownMenuItem(
                    text = { Text(text = RepairTypeSelect.CHANGE_OF_OIL.type) },
                    onClick = {
                        item = RepairTypeSelect.CHANGE_OF_OIL.type
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = RepairTypeSelect.CAR_WIPERS.type) },
                    onClick = {
                        item = RepairTypeSelect.CAR_WIPERS.type
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = RepairTypeSelect.BATTERY_REPLACEMENT.type) },
                    onClick = {
                        item = RepairTypeSelect.BATTERY_REPLACEMENT.type
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = RepairTypeSelect.GLASS_REPLACEMENT.type) },
                    onClick = {
                        item = RepairTypeSelect.GLASS_REPLACEMENT.type
                        isExpanded = false
                    }
                )
                DropdownMenuItem(
                    text = { Text(text = RepairTypeSelect.WIRING_REPLACEMENT.type) },
                    onClick = {
                        item = RepairTypeSelect.WIRING_REPLACEMENT.type
                        isExpanded = false
                    }
                )
            }
        }
    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyDateDialog(modifier: Modifier = Modifier) {

    var openDialog by remember {
        mutableStateOf(false)
    }

    val datePickerState = rememberDatePickerState()

    var dateItem by remember(datePickerState.selectedDateMillis) {
        mutableStateOf(datePickerState.selectedDateMillis?.let {
            SimpleDateFormat("dd/MM/yyyy").format(Date(it))
        } ?: "Выберите дату")
    }
    Surface(
        color = Color(0xFFF7F7F7),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = dateItem,
                modifier = Modifier.padding(start = 16.dp)
            )
            IconButton(onClick = { openDialog = true }) {
                Icon(Icons.Default.DateRange, contentDescription = null)
            }
        }
    }
    if (openDialog) {
        DatePickerDialog(
            onDismissRequest = { openDialog = false },
            confirmButton = {
                TextButton(onClick = { openDialog = false }) {
                    Text(text = "Ok")
                }
            }
        ) {
            DatePicker(state = datePickerState)
        }
    }
}

@Composable
fun MileageSurface(modifier: Modifier = Modifier,
                   text: String, subtext: String) {
    var mileageValue by remember {
        mutableStateOf("")
    }

    Surface(
        color = Color(0xFFF7F7F7),
        shape = MaterialTheme.shapes.extraSmall,
        modifier = modifier
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            OutlinedTextField(
                value = mileageValue,
                onValueChange = { it -> mileageValue = it },
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedBorderColor = Color(0xFFF7F7F7),
                    unfocusedBorderColor = Color(0xFFF7F7F7)
                ),
                modifier = Modifier.fillMaxWidth(),
                placeholder = {
                    Text(
                        text = text,
                        fontSize = 15.sp,
                        color = Color(0xFF2C2948)
                    )
                },
                trailingIcon = {
                    Text(
                        text = subtext,
                        fontSize = 15.sp,
                        color = Color(0xFF2C2948)
                    )
                },
            )

        }


    }
}

