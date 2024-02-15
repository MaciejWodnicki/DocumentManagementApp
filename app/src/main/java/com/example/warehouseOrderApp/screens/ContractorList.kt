package com.example.warehouseOrderApp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.repositories.ContractorService

var addActionActive = false
val contractorService:ContractorService = ContractorService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContractorList(navController: NavHostController) {


    val addActionState = remember {
        mutableStateOf(addActionActive)
    }
    Scaffold(

        topBar = {
            CenterAlignedTopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Lista Kontrahentów")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                addActionState.value = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Add")
                Text(text ="Add")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            items(contractorService.listOfContractors()){contractor ->
                Column (modifier = Modifier,
                    ){
                    ContractorEntry(contractor = contractor, navController)
                }
            }

        }
        if (addActionState.value){
            navController.navigate("${Routes.ContractorEdit.name}?contractor={${Contractor()}}")
            addActionState.value = false
        }
    }

}
@Composable
fun ContractorEntry(contractor: Contractor, navController:NavController){
    // Add padding around our message
    Row(
        modifier = Modifier
            .padding(all = 8.dp)
            .fillMaxWidth()
    ) {
        Column {
            Text(text = contractor.name)
            // Add a vertical space between the author and message texts
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = contractor.symbol)
            Divider(color = MaterialTheme.colorScheme.primary)
        }
        Button(onClick = {
            navController.navigate(
                "${Routes.ContractorEdit.name}?contractor={${contractor.toString()}}")},
            modifier = Modifier.background(color = Color.Red)
        ) {
            Icons.Default.Edit
        }
    }

}

@Composable
fun ContractorEdit(contractor: Contractor = Contractor(),
                   navController: NavController) {

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf(contractor.name) }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Name") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        var symbol by remember { mutableStateOf(contractor.symbol) }
        OutlinedTextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Symbol") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(onClick = {
            contractor.updateContractor(symbol,name)
            contractorService.addContractor(contractor)
            navController.navigate(Routes.Contractors.name)
        }) {
            Text(text = "Confirm Changes")
        }
    }

}