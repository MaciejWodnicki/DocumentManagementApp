package com.example.warehouseOrderApp.screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehouseOrderApp.src.data.Routes


@Composable
fun MainMenu(navController: NavController){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(0xFF2B74CD))
            .padding(horizontal = 10.dp, vertical = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,

        ){
        MenuButton(text = "Kontrahenci", navController, Routes.Contractors.name)
        MenuButton(text = "Lista Dokumentów", navController, Routes.DocumentList.name)
    }
}

@Composable
fun MenuButton(text: String, navController: NavController, destination:String) {
    Button(onClick = {
                     navController.navigate(destination)
    },
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(0.dp, 40.dp),
        modifier = Modifier
            .padding(0.dp, 5.dp)
            .fillMaxWidth()
            .height(250.dp),
        colors = ButtonDefaults.buttonColors(Color(0xFFD5D5D5)),
        border = BorderStroke(1.dp, Color.Black),


    ) {

        Text(text = text,
            fontSize = 50.sp,
            color = Color.Black,
            fontFamily = FontFamily.Serif,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp)

    }
}
