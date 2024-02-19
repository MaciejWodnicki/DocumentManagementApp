package com.example.warehouseOrderApp.screens

import androidx.activity.compose.BackHandler
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ElevatedButton
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.data.UnitOfMeasure
import com.example.warehouseOrderApp.src.repositories.ContractorsService
import com.example.warehouseOrderApp.src.repositories.DocumentsService


private val documentService: DocumentsService = DocumentsService
private val contractorsService: ContractorsService = ContractorsService

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentPreview(index: Long, navController: NavController) {

    Scaffold(
        topBar = {
            TopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Row (
                        modifier = Modifier.fillMaxWidth().
                        padding(end = 20.dp).
                        clickable {
                            navController.navigate(
                                "${Routes.DocumentEdit.name}/$index"
                            )
                        },
                        horizontalArrangement = Arrangement.SpaceBetween
                    ){
                        Text(text = "Dokument")
                        Icon(Icons.Default.Edit, contentDescription = "Dodaj towar")
                    }
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                navController.navigate(
                    "${Routes.EntryEdit.name}/$index/${documentService.get(index).availableIndex()}"
                )
            }) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj towar")
                Text(text = "Dodaj towar")
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
                    Data: ${documentService.get(index).date.toString()}
                    Symbol: ${documentService.get(index).symbol}
                    """
                    )
                }
                Column(horizontalAlignment = Alignment.End) {
                    Text(
                        text =
                        """
                    Kontrahent:
                    
                    Nazwa: ${contractorsService.get(documentService.get(index).contractor).name}
                    Symbol: ${contractorsService.get(documentService.get(index).contractor).symbol}
                    
                    
                """,
                    )
                }
            }

            Text(
                text = "Lista towarów: ",
                modifier = Modifier.padding(start = 20.dp)
            )

            LazyColumn(
                modifier = Modifier.padding(horizontal = 20.dp)
            )
            {
                itemsIndexed(documentService.get(index).getEntries()) { id, item ->
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .clickable {
                                navController.navigate(
                                    "${Routes.EntryPreview.name}/$index/$id"
                                )
                            }
                    ) {
                        Divider(Modifier.padding(top = 10.dp))
                        Text(
                            text = """
                            Nazwa:    ${documentService.get(index).getEntries()[id].name}
                            Ilość:    ${documentService.get(index).getEntries()[id].amount} ${documentService.get(index).getEntries()[id].unit}
                        """.trimIndent()
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun EntryEdit(
    index: Long, entryIndex: Long?,
    navController: NavController,
) {

    if (index == null ||
        entryIndex == null) return


    val documentEntry = documentService.get(index).getEntries()[entryIndex.toInt()]

    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var name by remember { mutableStateOf(documentEntry.name) }

        OutlinedTextField(
            value = name,
            onValueChange = { name = it },
            label = { Text("Nazwa") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        var amount by remember { mutableStateOf(documentEntry.amount.toString()) }

        OutlinedTextField(
            value = amount,
            onValueChange = { amount = it },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Decimal),
            label = { Text(text = "Nazwa") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        var unit = UnitSelection()

        ElevatedButton(onClick = {

            if (name == "") {
                documentService.get(index).removeEntry(entryIndex.toInt())
            } else {
                documentService.get(index).getEntries()[entryIndex.toInt()].name = name
                documentService.get(index).getEntries()[entryIndex.toInt()].amount = amount.toFloat()
                documentService.get(index).getEntries()[entryIndex.toInt()].unit = unit
            }

            navController.popBackStack()
        }) {
            Text(text = "Zatwierdź")
        }
        BackHandler() {
            if (name == "" || amount.toFloat() <=0.0f){
                documentService.get(index).removeEntry(entryIndex.toInt())
            }
            navController.popBackStack()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UnitSelection(): UnitOfMeasure {
    val units = UnitOfMeasure.entries.toTypedArray()

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var unit by remember { mutableStateOf(UnitOfMeasure.U) }

    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
            .background(Color.Red)
    ) {

        ExposedDropdownMenuBox(
            expanded = expanded,
            onExpandedChange = {
                expanded = !expanded
            }
        ) {
            TextField(
                value = selectedText,
                onValueChange = { selectedText = it },
                label = { Text(text = "Jednostka") },
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {}
            ) {
                units.forEach() { item ->
                    DropdownMenuItem(
                        text = { Text(text = "${item}") },
                        onClick = {
                            selectedText = "${item}"
                            expanded = false
                            unit = item
                        }
                    )
                }
            }

        }
    }
    return unit
}
