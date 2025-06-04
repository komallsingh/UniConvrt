package com.komal.unitconverter

import android.content.Context
import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.komal.unitconverter.ui.theme.UnitConverterTheme
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            UnitConverterTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->

                        UnitConverter()

                }
            }
        }
    }
}
@Composable
fun UnitConverter() {
    var input by remember{ mutableStateOf("") }
    var output by remember { mutableStateOf("") }
    var inputUnit by remember{ mutableStateOf("Meters") }
    var outputUnit by remember { mutableStateOf("Meters") }
    var inExpanded by remember{ mutableStateOf(false) }
    var outExpanded by remember { mutableStateOf(false) }
    val conversionFactor=remember{ mutableStateOf(1.00) }
    val oConversionFactor = remember{ mutableStateOf(1.00) }

    fun convertUnits(){
        val inputD=input.toDoubleOrNull()?:0.0 //?: elvis operator short if statement
        val result=(inputD*conversionFactor.value*100.0/oConversionFactor.value).roundToInt()/100.0
        output=result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //HERE ALL UI ELEMENTS WILL BE STACKED TOGETHER
        Text("UNIT CONVERTER", style=MaterialTheme.typography.headlineLarge)
        Spacer(modifier = Modifier.height(30.dp))
        OutlinedTextField(value = input, onValueChange = {
            input=it  //string value entered

        },
            label = { Text("Enter Value") }) //LABLE
        Spacer(modifier = Modifier.height(20.dp))
        Row {
//            val context= LocalContext.current
//            Button(onClick ={ Toast.makeText(context,
//                "Thanks for Clicking",Toast.LENGTH_LONG).show()})
//            {
//                Text("RESULT")
//            }
            Box { //input box
                Button(onClick = {inExpanded=true}) {
                    Text(text=inputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }
                Spacer(modifier = Modifier.height(16.dp))
                DropdownMenu(expanded = inExpanded, onDismissRequest = {inExpanded=false}) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        inExpanded=false
                        inputUnit="Centimeters"
                    conversionFactor.value= 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        inExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value= 1.0
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        inExpanded=false
                        inputUnit="Feet"
                        conversionFactor.value= 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        inExpanded=false
                        inputUnit="Millimeters"
                        conversionFactor.value= 0.001
                        convertUnits()
                    })
                }
            }
            Spacer(modifier = Modifier.height(20.dp))
            Box {
                Button(onClick = {outExpanded=true}) {
                    Text(text=outputUnit)
                    Icon(
                        Icons.Default.ArrowDropDown,
                        contentDescription = "Arrow Down"
                    )
                }

                DropdownMenu(expanded = outExpanded, onDismissRequest = {outExpanded=false}) {
                    DropdownMenuItem(text = { Text("Centimeters") }, onClick = {
                        outExpanded=false
                        outputUnit="Centimeters"
                        oConversionFactor.value= 0.01
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Meters") }, onClick = {
                        outExpanded=false
                        outputUnit="Meters"
                        oConversionFactor.value= 1.00
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Feet") }, onClick = {
                        outExpanded=false
                        outputUnit="Feet"
                        oConversionFactor.value= 0.3048
                        convertUnits()
                    })
                    DropdownMenuItem(text = { Text("Millimeters") }, onClick = {
                        outExpanded=false
                        outputUnit="Millimeters"
                        oConversionFactor.value= 0.001
                        convertUnits()
                    })
                }


            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        Text("RESULT: $output $outputUnit",
         style = MaterialTheme.typography.headlineMedium
        )}


}

@Preview(showBackground = true)
@Composable
fun UnitConverterPreview() {
    UnitConverter()
}