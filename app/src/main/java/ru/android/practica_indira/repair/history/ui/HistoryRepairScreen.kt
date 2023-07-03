package ru.android.practica_indira.repair.history.ui


import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import ru.android.practica_indira.R
import ru.android.practica_indira.add_car_second_step.AddTypeRepair
import ru.android.practica_indira.add_car_second_step.MileageSurface
import ru.android.practica_indira.add_car_second_step.MyDateDialog
import ru.android.practica_indira.repair.ui.testColor
import ru.android.practica_indira.repair.ui.testList


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HistoryRepairScreen(
    onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "История ремонта")
                },
                navigationIcon = {
                    IconButton(onClick = onBack) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = ""
                        )
                    }
                },
                actions = {
                    IconButton(onClick = { }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = ""
                        )
                    }
                }
            )
        }
    ) {
        Column(
            modifier = Modifier
                .padding(it)
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background)
        ) {
            Header()

            OverHead()

            Body(it.calculateTopPadding())
        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
            Lag(it.calculateBottomPadding())
        }
    }
}

@Composable
fun Body(calculateTopPadding: Dp) {
    LazyColumn(
        contentPadding = PaddingValues(
            bottom = calculateTopPadding + 20.dp,
            start = 16.dp,
            end = 16.dp,
            top = 16.dp
        ),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        modifier = Modifier
            .offset(x = 0.dp, y = (-78).dp)
            .zIndex(1f),
    ) {
        items(testList) {
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 24.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        painter = painterResource(id = it.icon),
                        contentDescription = "",
                        modifier = Modifier.padding(end = 16.dp)
                    )


                    Column {
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.type,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = it.date,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Light,
                                fontSize = 15.sp
                            )
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(
                                text = it.description,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = it.cost,
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                    }

                }
            }
        }
    }
}

@Composable
fun OverHead() {
    Surface(
        shadowElevation = 60.dp,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .offset(x = 0.dp, y = (-60).dp),
        shape = MaterialTheme.shapes.extraSmall
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(32.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
//            Box(
//                modifier = Modifier
//                    .padding(0.dp)
//                    .width(10.dp)
//                    .height(10.dp)
//                    .offset(x = 36.dp, y = 60.dp)
//                    .background(color = Color(0xFF7165E3), shape = RoundedCornerShape(size = 10.dp))
//            )

            Column {
                Text(
                    text = "2023",
                    fontSize = 12.sp,
                    color = Color(0xCC1C1939),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Row() {
                    Text(
                        text = "₽120",
                        fontSize = 26.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF1C1939),
                        modifier = Modifier.alignByBaseline()
                    )

                    Text(
                        text = "тыс.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1C1939),
                        modifier = Modifier.alignByBaseline()
                    )
                }

            }

            Column {
                Text(
                    text = "2022",
                    fontSize = 12.sp,
                    color = Color(0xCC1C1939),
                    modifier = Modifier.padding(bottom = 6.dp)
                )
                Row() {
                    Text(
                        text = "₽96",
                        fontSize = 26.sp,
                        fontWeight = FontWeight(700),
                        color = Color(0xFF1C1939),
                        modifier = Modifier.alignByBaseline()
                    )

                    Text(
                        text = "тыс.",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.Medium,
                        color = Color(0xFF1C1939),
                        modifier = Modifier.alignByBaseline()
                    )
                }

            }
        }
    }
}

@Composable
private fun Header() {
    Image(
        painter = painterResource(id = R.drawable.graph),
        contentDescription = "",
        modifier = Modifier
            .width(414.dp)
            .height(169.dp)
    )
}

@Composable
private fun Lag(calculateBottomPadding: Dp) {
    var addClick by remember {
        mutableStateOf(false)
    }
    Surface(
        modifier = Modifier
            .heightIn(min = 150.dp)
            .animateContentSize(),
        shape = RoundedCornerShape(
            topEnd = 40.dp,
            topStart = 40.dp
        ),
        shadowElevation = 60.dp
    ) {
        Column() {
            AnimatedVisibility(visible = addClick) {
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(top = 37.dp)
                        .padding(horizontal = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ) {
                    Surface(
                        modifier = Modifier.fillMaxWidth(),
                        color = Color(0xFFF7F7F7),
                        shape = MaterialTheme.shapes.extraSmall
                    ) {
                        AddTypeRepair(
                            menuName = "Вид ремонта",
                            modifier = Modifier
                        )
                    }


                    MyDateDialog()

                    MileageSurface(
                        text = "Стоимость ремонта", subtext = "₽"
                    )
                }

            }

            Button(
                onClick = { addClick = !addClick },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = calculateBottomPadding, top = 40.dp)
                    .padding(horizontal = 20.dp),
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Text(
                    text = "+ Добавить ремонт",
                    fontSize = 17.sp,
                    modifier = Modifier
                        .padding(horizontal = 10.dp, vertical = 10.dp)
                )
            }
        }


    }

}