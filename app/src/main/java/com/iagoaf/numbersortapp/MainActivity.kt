package com.iagoaf.numbersortapp

import android.R.attr.text
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.iagoaf.numbersortapp.ui.theme.NumberSortAppTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            NumberSortAppTheme {
                Greeting()
            }
        }
    }
}

@Composable
fun Greeting() {
    var startValue by remember { mutableStateOf("0") }
    var endValue by remember { mutableStateOf("100") }
    var nonRepeat by remember { mutableStateOf(false) }
    var result by remember { mutableStateOf(listOf<Int>(0)) }

    Scaffold(
        containerColor = Color(0xff020202),
        modifier = Modifier
            .fillMaxSize()
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .padding(horizontal = 32.dp)
                .fillMaxSize()
                .padding(paddingValues)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo_numbers),
                contentDescription = "ImageResource",
                modifier = Modifier
                    .height(65.dp)
                    .width(160.dp)
                    .align(Alignment.CenterHorizontally)
            )
            Text(
                "Online - gratuito",
                style = TextStyle(
                    brush = Brush.linearGradient(
                        colors = listOf(
                            Color(0xffC7C9CC),
                            Color(0xff3D3D3D)
                        )
                    ),
                    fontSize = 16.sp,
                )
            )
            Text(
                "SORTEADOR DE NUMEROS",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 40.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Spacer(
                modifier = Modifier.padding(top = 32.dp)
            )
            Text(
                "QUERO SORTEAR:",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold,
                ),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Text(
                "Defina o intervalo e a quantidade de números, clique em \"Sortear\" e veja os resultados na tela. É rápido e fácil!",
                style = TextStyle(
                    color = Color.White,
                    fontSize = 14.sp,
                ),
                color = Color(0xffD9D9D9),
                modifier = Modifier
                    .padding(top = 8.dp)
            )
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.fillMaxWidth()
            ) {
                CustomTextField(
                    value = result.last().toString(),
                    title = "NÚMEROS",
                    readOnly = true,
                    onValueChange = {
                    }
                )

                CustomTextField(
                    value = startValue,
                    title = "DE",
                    onValueChange = { newValue -> startValue = newValue },
                    modifier = Modifier.padding(horizontal = 8.dp)
                )

                CustomTextField(
                    value = endValue,
                    title = "DE",
                    onValueChange = { newValue -> endValue = newValue }
                )
            }

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.padding(top = 16.dp)
            ) {
                Checkbox(
                    checked = nonRepeat,
                    onCheckedChange = {
                        nonRepeat = it
                    },
                )
                Text(
                    "Não repetir números",
                    style = TextStyle(
                        color = Color.White
                    )
                )
            }

            Button(
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color(0xff24222E),
                ),
                shape = RoundedCornerShape(
                    8.dp
                ),
                onClick = {
                    val start = startValue.toIntOrNull() ?: 0
                    val end = endValue.toIntOrNull() ?: 0
                    if (start <= end) {
                        result = generateRandomNumbers(start, end, 5, nonRepeat)
                        Log.d("Result", result.toString())
                    }
                },
                contentPadding = PaddingValues(
                    vertical = 16.dp
                ),

                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 16.dp)
            ) {
               Row(
                   verticalAlignment = Alignment.CenterVertically
               ) {
                   Text(
                       "SORTEAR",
                       style = TextStyle(
                            fontSize = 16.sp,
                            fontWeight = FontWeight.Bold,
                       )
                   )
                   Image(
                      painter = painterResource(id = R.drawable.ic_arrow_right),
                      contentDescription = "Arrow",
                      colorFilter = ColorFilter.tint(Color.White),
                      modifier = Modifier
                        .padding(start = 8.dp),
                   )
               }
            }

        }
    }
}
@Composable
fun CustomTextField(
    modifier: Modifier = Modifier,
    title: String,
    value: String,
    readOnly: Boolean = false,
    onValueChange: (String) -> Unit,
){
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .padding(top = 32.dp)
    ) {
        Text(
            text = title,
            color = Color(0xffC7C9CC),
            style = TextStyle(
                fontWeight = FontWeight.Bold,
                fontSize = 16.sp,
            ),
            textAlign = TextAlign.Center
        )

        TextField(
            value = value,
            onValueChange = onValueChange,
            readOnly = readOnly,
            colors = TextFieldDefaults.colors(
                focusedContainerColor = Color(0xff111012),
                unfocusedContainerColor = Color(0xff111012),
                disabledContainerColor = Color(0xff111012),
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
            ),
            textStyle = TextStyle(
                color = Color.White,
                fontSize = 24.sp,
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold,
            ),
            modifier = modifier
                .width(84.dp)
                .padding(top = 8.dp)
        )
    }
}

fun generateRandomNumbers(
    start: Int,
    end: Int,
    count: Int,
    nonRepeat: Boolean
): List<Int> {
    if (start > end || count < 1) return emptyList()
    val range = (start..end).toList()
    return if (nonRepeat) {
        List(count) { range.random() }
    } else {
        range.shuffled().take(count)
    }
}

@Preview(showSystemUi = true, showBackground = true)
@Composable
fun GreetingPreview() {
    NumberSortAppTheme {
        Greeting()
    }
}