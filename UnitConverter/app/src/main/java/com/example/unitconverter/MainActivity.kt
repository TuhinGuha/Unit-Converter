package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colorScheme.background) {
                    UnitConverter()
                }
            }
        }
    }
}
@Composable
fun UnitConverter(){
    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inputExpanded by remember { mutableStateOf(false) }
    var outputExpanded by remember { mutableStateOf(false) }
    val inputConversionFactor=remember { mutableStateOf(1.00) }
    val outputConversionFactor=remember { mutableStateOf(1.00) }

    fun convertUnits(){
        val inputValueDouble= inputValue.toDoubleOrNull()?:0.0
    // toDoubleOrNull() says that if user inputs invalid values then it will return null
    // and ?: is Elvis operator which checks whether the input is  valid or not. If input is invalid then it turns it into 00.00
        val result= ((inputValueDouble*inputConversionFactor.value)/outputConversionFactor.value)
        outputValue= result.toString()
    }

    Column (modifier = Modifier.fillMaxSize(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.Center){
        Text(text = "Unit Converter", style = MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.padding(16.dp))
        OutlinedTextField(value = inputValue,
            onValueChange = {
                inputValue=it
                convertUnits()
                },
            label = {Text(text = "Enter the value", style = MaterialTheme.typography.headlineSmall)})
        Spacer(modifier = Modifier.padding(16.dp))
        Row {
            /*val context= LocalContext.current
            Button(onClick = { Toast.makeText(context,"Hey there!",Toast.LENGTH_LONG).show()}) {
                Text(text = "Click the button")
            }*/
            Box{
                Button(onClick = {inputExpanded=true}) {
                    Text(text = inputUnit)
                    Icon(
                        imageVector = Icons.Default.ArrowDropDown,
                        contentDescription = "Accesability feature for disabled person"
                    )
                }
                DropdownMenu(expanded =inputExpanded, onDismissRequest = {inputExpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Meters")},
                        onClick = {
                            inputExpanded=false
                            inputUnit="Meters"
                            inputConversionFactor.value=1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Centimeters")},
                        onClick = {
                            inputExpanded=false
                            inputUnit="Centimeters"
                            inputConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Kilometers")},
                        onClick = {
                            inputExpanded=false
                            inputUnit="Kilometers"
                            inputConversionFactor.value=1000.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Milimeters")},
                        onClick = {
                            inputExpanded=false
                            inputUnit="Milimeters"
                            inputConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
            Spacer(modifier =Modifier.width(16.dp))
            Box {
                Button(onClick = {outputExpanded=true}) {
                    Text(text = outputUnit)
                    Icon(imageVector = Icons.Default.ArrowDropDown, contentDescription ="Accesability feature for disabled person" )
                }
                DropdownMenu(expanded =outputExpanded, onDismissRequest = {outputExpanded=false}) {
                    DropdownMenuItem(text = { Text(text = "Meter")},
                        onClick = {
                            outputExpanded=false
                            outputUnit="Meters"
                            outputConversionFactor.value=1.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Centimeters")},
                        onClick = {
                            outputExpanded=false
                            outputUnit="Centimeters"
                            outputConversionFactor.value=0.01
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Kilometers")},
                        onClick = {
                            outputExpanded=false
                            outputUnit="Kilometers"
                            outputConversionFactor.value=1000.00
                            convertUnits()
                        }
                    )
                    DropdownMenuItem(text = { Text(text = "Milimeters")},
                        onClick = {
                            outputExpanded=false
                            outputUnit="Milimeters"
                            outputConversionFactor.value=0.001
                            convertUnits()
                        }
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text(text = "Result: $outputValue", style = MaterialTheme.typography.headlineMedium)


    }
}
@Preview(showBackground = true)
@Composable
fun UnitConverterPreview(){
    UnitConverter()
}





