package com.example.averagemarkcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clipScrollableContainer
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.paddingFromBaseline
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.modifier.modifierLocalConsumer
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.averagemarkcompose.ui.theme.AverageMarkComposeTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge(
            statusBarStyle = SystemBarStyle.dark(
                android.graphics.Color.TRANSPARENT
            )
        )
        super.onCreate(savedInstanceState)
        setContent {
            AverageMarkComposeTheme {
                MainScreen()
            }
        }
    }
}

private fun removeLastNchars(str: String, n: Int): String {
    return str.substring(0, str.length - n)
}

var FinalRes: String = "0"
var FinalCount: String = ""

var res = 0f
var cou = 0f
var num = 0f

var Numbers = arrayListOf(cou)

@Composable
fun MainScreen (){

    val FinalRess = remember {
        mutableStateOf("0")
    }
    val FinalCountt = remember {
        mutableStateOf("")
    }

    Box (
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.secondary),
    ){
        Column {
            Card (
                modifier = Modifier
                    .fillMaxWidth()
                    .height(580.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary),
                shape = RoundedCornerShape(0.dp),
                elevation = CardDefaults.cardElevation(15.dp)
            ){
                Column (
                    modifier = Modifier
                        .fillMaxSize()
                ){
                    Card (
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(480.dp),
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                        shape = RoundedCornerShape(0.dp),
                        elevation = CardDefaults.cardElevation(15.dp)
                    ){

                        Column (
                            modifier = Modifier
                                .fillMaxSize(),
                            verticalArrangement = Arrangement.Bottom
                        ){
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(200.dp)
                                    .padding(end = 40.dp),
                                contentAlignment = Alignment.CenterEnd
                            ){
                                Text (
                                    text = FinalRess.value,
                                    fontSize = 60.sp,
                                    color = MaterialTheme.colorScheme.onBackground
                                )

                            }
                            Box (
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(100.dp)
                                    .padding(end = 40.dp, start = 40.dp),
                                contentAlignment = Alignment.CenterEnd
                            ){
                                Text (
                                    text = FinalCountt.value,
                                    maxLines = 1,
                                    overflow = TextOverflow.Ellipsis,
                                    fontSize = 30.sp,
                                    color = MaterialTheme.colorScheme.onBackground,
                                )
                            }
                        }
                    }


                    Row (
                        modifier = Modifier
                            .fillMaxSize(),
                        horizontalArrangement = Arrangement.End
                    ){
                        Button(
                            modifier = Modifier
                                .size(102.dp, 100.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            onClick = {
                                res = 0f
                                cou = 0f
                                num = 0f
                                Numbers = arrayListOf(cou)
                                FinalRes = "0"
                                FinalCount = ""

                                FinalRess.value = FinalRes
                                FinalCountt.value = FinalCount
                            }
                        ){
                            Text(
                                text = "CE",
                                fontSize = 30.sp,
                                color = Color(0xFFF2F2F2)
                            )
                        }

                        Button(
                            modifier = Modifier
                                .size(102.dp, 100.dp),
                            shape = RectangleShape,
                            colors = ButtonDefaults.buttonColors(Color.Transparent),
                            onClick = {
                                if(cou > 0f){
                                    FinalCount = removeLastNchars(FinalCount, 2)
                                    Numbers.remove(Numbers[cou.toInt()])
                                    cou -= 1f
                                    res = Numbers.sum()
                                    var s = (res / cou)
                                    if (s.isNaN())
                                        s = 0f
                                    else
                                        s = ((s * 10).roundToInt() / 10.0).toFloat()
                                    FinalRes = s.toString()
                                    if(FinalRes == "0.0")
                                        FinalRes = "0"
                                    FinalRess.value = FinalRes
                                    FinalCountt.value = FinalCount
                                }
                            }
                        ){
                            Image(
                                painter = painterResource(id = R.drawable.ic_delete),
                                contentDescription = "BackIcn"
                            )
                        }
                    }
                }
            }

            Row (
                modifier = Modifier
                    .fillMaxSize(),
                horizontalArrangement = Arrangement.End
            ) {
                Button( //2 button
                    modifier = Modifier
                        .size(102.dp, 100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        num = 2f
                        Numbers.add(num)
                        cou++
                        FinalCount += "${num.toInt()} "

                        res = Numbers.sum()
                        var s = (res / cou)
                        if (s.isNaN())
                            s = 0f
                        else
                            s = ((s * 10).roundToInt() / 10.0).toFloat()
                        FinalRes = s.toString()
                        if(FinalRes == "0.0")
                            FinalRes = "0"
                        FinalRess.value = FinalRes
                        FinalCountt.value = FinalCount
                    }
                ) {
                    Text(
                        text = "2",
                        fontSize = 30.sp,
                        color = Color(0xFFF2F2F2)
                    )
                }

                Button( //3 button
                    modifier = Modifier
                        .size(102.dp, 100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        num = 3f
                        Numbers.add(num)
                        cou++
                        FinalCount += "${num.toInt()} "

                        res = Numbers.sum()
                        var s = (res / cou)
                        if (s.isNaN())
                            s = 0f
                        else
                            s = ((s * 10).roundToInt() / 10.0).toFloat()
                        FinalRes = s.toString()
                        if(FinalRes == "0.0")
                            FinalRes = "0"
                        FinalRess.value = FinalRes
                        FinalCountt.value = FinalCount
                    }
                ) {
                    Text(
                        text = "3",
                        fontSize = 30.sp,
                        color = Color(0xFFF2F2F2)
                    )
                }
                Button( //4 button
                    modifier = Modifier
                        .size(102.dp, 100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        num = 4f
                        Numbers.add(num)
                        cou++
                        FinalCount += "${num.toInt()} "

                        res = Numbers.sum()
                        var s = (res / cou)
                        if (s.isNaN())
                            s = 0f
                        else
                            s = ((s * 10).roundToInt() / 10.0).toFloat()
                        FinalRes = s.toString()
                        if(FinalRes == "0.0")
                            FinalRes = "0"
                        FinalRess.value = FinalRes
                        FinalCountt.value = FinalCount
                    }
                ) {
                    Text(
                        text = "4",
                        fontSize = 30.sp,
                        color = Color(0xFFF2F2F2)
                    )
                }

                Button( //5 button
                    modifier = Modifier
                        .size(102.dp, 100.dp),
                    shape = RectangleShape,
                    colors = ButtonDefaults.buttonColors(Color.Transparent),
                    onClick = {
                        num = 5f
                        Numbers.add(num)
                        cou++
                        FinalCount += "${num.toInt()} "

                        res = Numbers.sum()
                        var s = (res / cou)
                        if (s.isNaN())
                            s = 0f
                        else
                            s = ((s * 10).roundToInt() / 10.0).toFloat()
                        FinalRes = s.toString()
                        if(FinalRes == "0.0")
                            FinalRes = "0"
                        FinalRess.value = FinalRes
                        FinalCountt.value = FinalCount
                    }
                ) {
                    Text(
                        text = "5",
                        fontSize = 30.sp,
                        color = Color(0xFFF2F2F2)
                    )
                }
            }
        }
    }
}