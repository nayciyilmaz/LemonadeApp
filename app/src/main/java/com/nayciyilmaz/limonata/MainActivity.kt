package com.nayciyilmaz.limonata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.nayciyilmaz.limonata.ui.theme.LimonataTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LimonataTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Preview
@Composable
fun LemonadeApp(){
    LemonadeImage(modifier = Modifier
        .fillMaxWidth()
        .wrapContentSize(Alignment.Center)
        .background(Color(0xFFFFFFFF)),
    )
}

@Composable
fun LemonadeImage(modifier: Modifier = Modifier){
    var result by remember { mutableStateOf(1) }
    var squeezeCount by remember { mutableStateOf(0) }

    val imageResult = when(result){
        1 -> R.drawable.lemon_tree
        2 -> R.drawable.lemon_squeeze
        3 -> R.drawable.lemon_drink
        else -> R.drawable.lemon_restart
    }

    val stringResult = when(result){
        1 -> R.string.lemon_tree
        2 -> R.string.lemon
        3 -> R.string.glass_of_lemonade
        else -> R.string.empty_glass
    }

    Column(horizontalAlignment = Alignment.CenterHorizontally,
        ) {
        Text(
            text = "LEMONADE",
            modifier = Modifier.fillMaxWidth().height(80.dp).background(Color(0xFFFAE108)).padding(top = 35.dp),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold,
            fontSize = 22.sp
        )
    }

    Column(modifier = modifier, horizontalAlignment = Alignment.CenterHorizontally,verticalArrangement = Arrangement.Center) {
        Image(painter = painterResource(imageResult), contentDescription = result.toString() ,
            modifier = Modifier
                .clickable {
                    if (result == 1) {
                        result++
                        squeezeCount = (2..4).random()
                    } else if (result == 2) {
                        squeezeCount--
                        if (squeezeCount == 0) {
                            result++
                        }
                    } else if (result == 3) {
                        result++
                    } else {
                        result = result % 3
                    }
                }
                .background(Color(0xFFD0F7A3), shape = RoundedCornerShape(12))
                .size(250.dp)
                .border(2.dp, Color(0xFFBAE786), shape = RoundedCornerShape(12))
        )
        Text(text = stringResource(stringResult),Modifier.padding(top = 20.dp))
    }
}


