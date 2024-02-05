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

class MainActivity: ComponentActivity() {
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
    var calcMode by remember { mutableStateOf("plus") }
    var result by remember { mutableStateOf("") }

    val radioOptions = listOf("plus", "minus", "mal", "durch")
    val (selectedOption, onOptionSelected) = remember { mutableStateOf(radioOptions[0]) }

    var output by remember { mutableStateOf("") }
    var input1 by remember { mutableStateOf("") }
    var input2 by remember { mutableStateOf("") }

    Scaffold(
    ) { innerpadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerpadding),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
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
                    if (input1.isEmpty() || input2.isEmpty()) {
                        longToast("BittegebenSiezweiOperandenan")
                    } else {
                        val x = input1.toInt()
                        val y = input2.toInt()
                        when (calcMode) {
                            "plus" -> result = (x + y).toString()
                            "minus" -> result = (x - y).toString()
                            "mal" -> result = (x * y).toString()
                            "durch" -> result = (x / y).toString()
                        }
                        output = result
                    }
                },
                border = BorderStroke(2.dp, Color.Blue),
                shape = RoundedCornerShape(20),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Blue
                )
            ) {
                Text(
                    text = when (calcMode) {
                        "plus" -> "+"
                        "minus" -> "-"
                        "mal" -> "x"
                        "durch" -> "/"
                        else -> "+"
                    }
                )
            }
            Text(
                text = "="
            )
            Text(
                modifier = Modifier.padding(vertical = 20.dp),
                text = output
            )
            radioOptions.forEach { text ->
                Row(
                    Modifier
                        .fillMaxWidth()
                        .height(56.dp)
                        .selectable(
                            selected = (text == selectedOption),
                            onClick = {
                                onOptionSelected(text)
                                calcMode = text
                            },
                            role = Role.RadioButton
                        )
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    RadioButton(
                        selected = (text == selectedOption),
                        onClick = {
                            onOptionSelected(text)
                            calcMode = text
                        }
                    )
                    Text(
                        text = text,
                        style = MaterialTheme.typography.bodyLarge,
                        modifier = Modifier.padding(start = 16.dp)
                    )
                }
            }
        }
    }
}


@Preview(showBackground=true)
@Composable
fun SimpleButtonPreview() {
    SimpleCalcAppJCTheme {
        SimpleButton()
    }
}