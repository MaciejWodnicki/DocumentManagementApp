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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.AbsoluteAlignment
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.repositories.ContractorsService
import com.example.warehouseOrderApp.src.repositories.DocumentsService
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.LocalDate


private val documentService:DocumentsService = DocumentsService
private val contractorService: ContractorsService = ContractorsService
private val addActionActive = false


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentList(navController: NavHostController) {

    val addActionState = remember { mutableStateOf(addActionActive) }
    contractorService.addContractor(Contractor("/no1/asd","no1"))
    documentService.addDocument("asd/asd", LocalDate.of(2012,12,22),0)
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
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
            itemsIndexed(documentService.listOfDocuments()){index, item ->
                Column (modifier = Modifier,
                ){
                    DocumentListing(index, navController)
                }
            }

        }
        if (addActionState.value){
            navController.navigate(
                "${Routes.ContractorEdit.name}/${documentService.availableIndex()}")
            addActionState.value = false
        }
    }

}
@Composable
fun DocumentListing(index: Int, navController: NavController){

    val document = documentService.data(index)
    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp )
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = document.symbol)
            Text(text = contractorService.listOfContractors()[document.contractor].name)
        }

        Column {
            Text(text = document.date.toString(),
            )
        }

    }
    Row{
        Divider(color = MaterialTheme.colorScheme.primary)
    }

}


@Composable
fun DocumentEdit(index: Int?, navController: NavController) {

    if(index == null) return


    val document = documentService.data(index)
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var symbol by remember { mutableStateOf(document.symbol) }

        OutlinedTextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Symbol") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        var date by remember { mutableStateOf(document.date) }
        OutlinedTextField(
            value = date.toString(),
            onValueChange = { date = LocalDate.parse(it) },
            label = { Text("Symbol") }
        )

        var contractor by remember { mutableStateOf(document.contractor.toString()) }
        OutlinedTextField(
            value = date.toString(),
            onValueChange = { date = LocalDate.parse(it) },
            label = { Text("Symbol") }
        )

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(onClick = {
        }) {
            Text(text = "Confirm")
        }
    }

}
