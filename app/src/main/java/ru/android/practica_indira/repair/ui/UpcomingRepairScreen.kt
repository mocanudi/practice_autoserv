package ru.android.practica_indira.repair.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.android.practica_indira.R


data class TypeRepair(
    val type: String,
    val description: String,
    val icon: Int,
    val now: Float,
    val max: Float,
    val cost: String,
    val date: String
)
val testColor = listOf<Color>(
    Color.Blue,
    Color.Red,
    Color.Green,
    Color.Cyan
)

val testList = listOf(
    TypeRepair(
        type = "Двигатель",
        description = "Замена масла",
        icon = R.drawable.vector_2,
        now = 1000f,
        max = 1442f,
        cost = "₽5тыс.",
        date = "01.02.2023"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Стойки амортизаторв",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2442f,
        cost = "₽54тыс.",
        date = "01.02.2023"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Сайлент блоки",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2942f,
        cost = "₽1тыс.",
        date = "05.02.2022"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Шаровая опора",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2442f,
        cost = "₽5тыс.",
        date = "01.02.2023"
    ),
    TypeRepair(
        type = "Двигатель",
        description = "Замена масла",
        icon = R.drawable.vector_2,
        now = 1000f,
        max = 1442f,
        cost = "₽6тыс.",
        date = "10.10.2023"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Стойки амортизаторв",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2442f,
        cost = "₽66тыс.",
        date = "10.10.2023"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Сайлент блоки",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2942f,
        cost = "₽76тыс.",
        date = "9.10.2020"
    ),
    TypeRepair(
        type = "Подвеска",
        description = "Шаровая опора",
        icon = R.drawable.pendant,
        now = 1000f,
        max = 2442f,
        cost = "₽6тыс.",
        date = "10.10.2023"
    ),
)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun UpcomingRepairScreen(onHistory: () -> Unit,
                         onBack: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Text(text = "Предстоящий ремонт", color = Color.White)
                },
                colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                ),
                navigationIcon = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.ArrowBack,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                },
                actions = {
                    IconButton(onClick = {  }) {
                        Icon(
                            imageVector = Icons.Default.Menu,
                            contentDescription = "",
                            tint = Color.White
                        )
                    }
                }
            ) 
        }
    ) {
        Column(modifier = Modifier
            .padding(it)
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.background)) {
            Header()

            Body(it.calculateTopPadding())
            

        }

        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter){
            Lag(it.calculateBottomPadding(), onHistory)
        }
    }
}

@Composable
private fun Lag(calculateBottomPadding: Dp, onHistory: () -> Unit) {
    Surface(
        modifier = Modifier.height(150.dp),
        shape = RoundedCornerShape(
            topEnd = 40.dp,
            topStart = 40.dp
        )
    ) {
        Button(onClick = onHistory,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = calculateBottomPadding, top = 40.dp)
                .padding(horizontal = 20.dp),
            shape = MaterialTheme.shapes.extraSmall
        ) {
            Text(text = "История ремонта",fontSize = 17.sp,
                modifier = Modifier
                    .padding(horizontal = 100.dp, vertical = 10.dp)
            )
        }
    }

}

@Composable
fun Body(calculateTopPadding: Dp) {
    LazyColumn(
        contentPadding = PaddingValues(bottom = calculateTopPadding + 20.dp, start = 16.dp, end = 16.dp, top = 16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ){
        items(testList){
            Card(
                modifier = Modifier.fillMaxWidth(),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(containerColor = Color.White)
            ) {
                Row(modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 20.dp),
                    verticalAlignment = Alignment.CenterVertically) {

                    Icon(painter = painterResource(id = it.icon), contentDescription = "")
                    Spacer(modifier = Modifier.padding(end = 16.dp))
                    Column {
                        Row(modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween) {
                            Text(text = it.type, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Light, fontSize = 15.sp)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(text = "через", style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Light,fontSize = 15.sp)
                        }
                        Row(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            Text(text = it.description, style = MaterialTheme.typography.labelSmall, fontWeight = FontWeight.Bold, fontSize = 16.sp)
                            Spacer(modifier = Modifier.padding(4.dp))
                            Text(
                                text = "${it.max - it.now} км",
                                style = MaterialTheme.typography.labelSmall,
                                fontWeight = FontWeight.Bold,
                                fontSize = 16.sp
                            )
                        }
                        Spacer(modifier = Modifier.padding(4.dp))
                      LinearProgressIndicator(
                          progress = (it.now) / it.max,
                          modifier = Modifier.fillMaxWidth(),
                          color = testColor.random(),
                          strokeCap = StrokeCap.Round
                      )
                    }
                }
            }
        }
    }
}




@Composable
fun Header() {
    val selected = remember{
        mutableStateOf(1)
    }
    Surface(
        color = MaterialTheme.colorScheme.primary,
        modifier = Modifier.fillMaxWidth(),
        shape = RoundedCornerShape(
            bottomEnd = 40.dp,
            bottomStart = 40.dp
        )
    ) {
        Row(modifier = Modifier
            .padding(horizontal = 16.dp)
            .padding(bottom = 40.dp, top = 20.dp),
            horizontalArrangement = Arrangement.SpaceBetween) {
            CardItem(R.drawable.all, "Все", selected.value){
                selected.value = it
            }

            CardItem(R.drawable.sto, "Кузов", selected.value){
                selected.value = it
            }

            CardItem(R.drawable.pendant, "Подвеска", selected.value){
                selected.value = it
            }

            CardItem(R.drawable.vector_2, "Двигатель", selected.value){
                selected.value = it
            }
        }


    }
}


@Composable
fun CardItem(id: Int, text: String, selected: Int, onClick: (Int) -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        Surface(
            onClick = {
                onClick(id)
            },
            color =  if (selected == id) Color(0x649E94FF) else Color(0x64665AD9),
            modifier = Modifier.size(62.dp),
            shape = RoundedCornerShape(16.dp)
        ) {
            Icon(
                painter = painterResource(id = id),
                contentDescription = "",
                tint = Color.White,
                modifier = Modifier.padding(22.dp)
            )
        }

        Text(text = text, color = Color.White, modifier = Modifier.padding(top = 6.dp))
    }

}