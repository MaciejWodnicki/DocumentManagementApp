package com.example.warehouseOrderApp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.repositories.ContractorsService
import com.example.warehouseOrderApp.src.repositories.DocumentsService


private val documentService: DocumentsService = DocumentsService
private val contractorsService: ContractorsService = ContractorsService
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun EntryPreview (documentId:Long, entryId:Long, navController: NavController){

    val entry = documentService.getEntry(entryId)
    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text(text = "Pozycja dokumentu")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate(
                    "${Routes.EntryEdit.name}/$documentId/$entryId"
                )
            }) {
                Icon(Icons.Default.Edit, contentDescription = "Edytuj")
                Text(text = "Edytuj")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding),
        ) {
            Row(
                horizontalArrangement = Arrangement.Start,
                modifier = Modifier.padding(end = 20.dp)
            ) {
                Column {
                    Text(
                        text = """
                    Nazwa Towaru: ${entry.name}
                    Symbol: ${entry.amount} ${entry.unit}
                    """
                    )
                }
            }
        }
    }

}