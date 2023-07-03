package ru.android.practica_indira.main_screen.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Brush

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.PaintingStyle.Companion.Stroke
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import ru.android.practica_indira.R


enum class TypeCard{
    One,
    Two,
    Three,
    Four
}

@OptIn(ExperimentalLayoutApi::class, ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(
    onRepairScreen: () -> Unit
) {
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                title = { Text(text = "Мой автомобиль") }
            )
        },
    ) { innerPadding ->
        LazyColumn(
            contentPadding = PaddingValues(
                top= innerPadding.calculateTopPadding(),
                bottom = innerPadding.calculateBottomPadding()
            )
        ) {
            item {
                Header()
            }

            item {
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Body(Color.Blue, TypeCard.One, 442f, 540f, onRepairScreen)

                    Body(Color.Red, TypeCard.One, 1442f, 2400f, onRepairScreen)
                }

            }

            item {
                Spacer(modifier = Modifier.size(16.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.spacedBy(16.dp)
                ) {
                    Body(Color.Red, TypeCard.Two, onRepairScreen = onRepairScreen)

                    Body(Color.Red, TypeCard.Three, onRepairScreen = onRepairScreen)
                }

            }

        }
    }
}

@Composable
fun Body(color: Color,typeCard: TypeCard,now: Float=0f,
         max: Float=0f, onRepairScreen: () -> Unit) {
    
    InfoCard(color, typeCard, now, max){
        when(it){
            TypeCard.Three -> onRepairScreen()
            else -> {}
        }
    }

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InfoCard(
    color: Color,
    typeCard: TypeCard,
    now: Float=0f,
    max: Float=0f,
    onRepairScreen: (TypeCard) -> Unit
) {
    Card(
        onClick = {
            onRepairScreen(typeCard)
        },
        modifier = Modifier.requiredSize(180.dp, 170.dp),
        colors = CardDefaults.cardColors(containerColor = Color.White),
    ) {
       when(typeCard){
           TypeCard.One -> {
               Box(
                   modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center){
                   DrawSemicircle(Modifier, color, max = max, now = now)
                   Column(horizontalAlignment = Alignment.CenterHorizontally) {
                       Text(
                           text = "через",
                           textAlign = TextAlign.Center,
                           fontSize = 11.sp
                       )
                       Text(text = "$now км",fontSize = 11.sp)

                   }
                   Box(modifier = Modifier
                       .fillMaxSize()
                       .padding(16.dp),
                       contentAlignment = Alignment.BottomCenter){
                       Text(text = "Замена масла",
                           style = MaterialTheme.typography.titleMedium)
                   }
               }
           }
           TypeCard.Two -> {
               Box(modifier = Modifier.fillMaxSize(),
               contentAlignment = Alignment.Center){
                   Column(horizontalAlignment = Alignment.CenterHorizontally) {
                       Icon(
                           imageVector = ImageVector.vectorResource(id = R.drawable.sto),
                           contentDescription = "",
                           tint = Color(0xFF4BC1E7)
                       )
                       Text(text = "СТО", style = MaterialTheme.typography.titleMedium)
                        Spacer(modifier = Modifier.size(16.dp))

                       Text(text = "\"Автолидер\"", style = MaterialTheme.typography.titleSmall)

                       Text(text = "ближайшее СТО", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light))
                   }

               }
           }
           TypeCard.Three -> {
               Box(modifier = Modifier.fillMaxSize(),
                   contentAlignment = Alignment.Center){
                   Column(horizontalAlignment = Alignment.CenterHorizontally) {
                       Icon(
                           imageVector = ImageVector.vectorResource(id = R.drawable.repair),
                           contentDescription = ""
                       )
                       Text(text = "Ремонт", style = MaterialTheme.typography.titleMedium)
                       Spacer(modifier = Modifier.size(16.dp))

                       Text(text = "~ 35 000 ₽", style = MaterialTheme.typography.titleSmall)

                       Text(text = "в следующем месяце", style = MaterialTheme.typography.labelSmall.copy(fontWeight = FontWeight.Light))
                   }

               }
           }
           TypeCard.Four -> TODO()
       }
        


    }
}

@Composable
fun DrawSemicircle(
    modifier: Modifier,
    color: Color,
    now: Float,
    max: Float,
) {
    Canvas(
        modifier = modifier.fillMaxSize(),
        onDraw = {
            drawArc(
                color = Color.Gray,
                startAngle = 140f,
                sweepAngle = 260f,
                useCenter = false,
                size = Size(220f,220f),
                style = Stroke(width = 6.dp.toPx()),
                topLeft = Offset(x = 130f, y = 120f)
            )

            drawArc(
                color = color,
                startAngle = 140f,
                sweepAngle = (now * 260f) / max,
                useCenter = false,
                size = Size(220f,220f),
                style = Stroke(width = 6.dp.toPx()),
                topLeft = Offset(x = 130f, y = 120f)
            )

        }
    )
}

@Composable
fun Header() {
    Surface(
        shape = RoundedCornerShape(
            bottomEnd = 40.dp,
            bottomStart = 40.dp
        )
    ) {
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Row(horizontalArrangement = Arrangement.Center,
                modifier = Modifier.fillMaxWidth()) {
                Surface(
                    shape =MaterialTheme.shapes.medium,
                    modifier = Modifier.size(240.dp,160.dp),
                    color = Color.Black
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.my_car),
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxWidth(),
                        contentScale = ContentScale.FillBounds

                    )

                }
            }

            Text(
                text = "Москвич 3",
                style = MaterialTheme.typography.titleLarge.copy(fontWeight = FontWeight.Bold)
            )

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "2023 г. в.",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "1,5 л., бензин",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "149,6 л. с.",
                    style = MaterialTheme.typography.labelSmall
                )
            }

            Row(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
            ) {
                Text(
                    text = "вариатор",
                    style = MaterialTheme.typography.labelSmall
                )
                Text(
                    text = "4WD",
                    style = MaterialTheme.typography.labelSmall
                )
            }


            Surface(
                color = MaterialTheme.colorScheme.secondary,
                shape = MaterialTheme.shapes.extraSmall
            ) {
                Row(verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier.padding(horizontal = 16.dp, vertical = 6.dp),) {
                    Text(
                        text = "024558",
                        fontSize = 44.sp,

                        fontWeight = FontWeight.Bold
                    )

                    Text(
                        text = "км",
                        fontSize = 20.sp,
                        modifier = Modifier.padding(start = 6.dp)
                    )
                }

            }
            Spacer(modifier = Modifier.size(40.dp))
        }
    }
   



}