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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.material.icons.rounded.AccountBox
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.LinearGradientShader
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.warehouseOrderApp.src.data.Routes


@Composable
fun MainMenu(navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(
                Brush.verticalGradient(
                    listOf(
                        MaterialTheme.colorScheme.background,
                        MaterialTheme.colorScheme.inversePrimary
                    )
                )
            )
            .padding(horizontal = 10.dp, vertical = 70.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Bottom,

        ) {
        
        MenuButton(text = "Kontrahenci", navController, Routes.Contractors.name)
        MenuButton(text = "Lista Dokumentów", navController, Routes.DocumentList.name)
    }
}

@Composable
fun MenuButton(text: String, navController: NavController, destination: String) {
    ElevatedButton(
        onClick = {
            navController.navigate(destination)
        },
        shape = RoundedCornerShape(15.dp),
        contentPadding = PaddingValues(0.dp, 40.dp),
        modifier = Modifier
            .padding(0.dp, 16.dp)
            .width(300.dp)
            .height(150.dp),
        colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.onSecondary),
        border = BorderStroke(1.dp, MaterialTheme.colorScheme.primary),


        ) {
        Text(
            text = text,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            color = MaterialTheme.colorScheme.scrim,
            fontFamily = MaterialTheme.typography.bodyLarge.fontFamily,
            textAlign = TextAlign.Center,
            lineHeight = 50.sp
        )
    }
}
