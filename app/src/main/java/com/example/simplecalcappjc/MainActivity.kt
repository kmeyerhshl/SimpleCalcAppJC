package com.example.simplecalcappjc

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.simplecalcappjc.ui.theme.SimpleCalcAppJCTheme
import splitties.toast.longToast

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SimpleCalcAppJCTheme {
                SimpleButton()
            }
        }
    }
}

private val TAG = "MainActivity"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SimpleButton() {
    var output by remember { mutableStateOf("") }
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }


    Scaffold(
    ) { innerpadding ->
        Column (
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            Row(
                modifier = Modifier.padding(16.dp),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                TextField(
                    value = input1,
                    onValueChange = {
                        input1 = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier
                        .weight(1f)
                        .padding(end = 8.dp)
                )
                TextField(
                    value = input2,
                    onValueChange = {
                        input2 = it
                    },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Number
                    ),
                    modifier = Modifier.weight(1f)
                )
            }
            Button(
                modifier = Modifier.padding(vertical = 20.dp),
                onClick = {
                    if(input1.isEmpty() || input2.isEmpty()){
                        longToast("Bitte geben Sie zwei Operanden an")
                    } else {
                        output = (input1.toInt() + input2.toInt()).toString()
                    }
                },
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue
                )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = null
                )
            }
            Text(
                text = "="
            )
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = output
            )
        }
    }
}


@Preview(showBackground = true)
@Composable
fun SimpleButtonPreview() {
    SimpleCalcAppJCTheme {
        SimpleButton()
    }
}