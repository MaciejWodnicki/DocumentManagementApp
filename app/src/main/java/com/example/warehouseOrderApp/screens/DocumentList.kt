package com.example.warehouseOrderApp.screens

import android.R
import android.widget.Toast
import androidx.activity.compose.BackHandler
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.Divider
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
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Document
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.repositories.ContractorsService
import com.example.warehouseOrderApp.src.repositories.DocumentsService
import kotlinx.coroutines.CoroutineName
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import java.time.LocalDate
import java.time.format.DateTimeParseException


private val documentService: DocumentsService = DocumentsService
private val contractorService: ContractorsService = ContractorsService
private val addActionActive = false


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DocumentList(navController: NavHostController) {

    documentService.provideCoroutineContext(CoroutineName("Document list view context"))

    val addActionState = remember { mutableStateOf(addActionActive) }
    Scaffold(
        topBar = {
            CenterAlignedTopAppBar(
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
                ),
                title = {
                    Text("Lista Dokumentów")
                }
            )
        },
        floatingActionButton = {
            ExtendedFloatingActionButton(onClick = {
                addActionState.value = true
            }) {
                Icon(Icons.Default.Add, contentDescription = "Dodaj")
                Text(text = "Dodaj")
            }
        }
    ) { innerPadding ->
        LazyColumn(
            modifier = Modifier
                .padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(4.dp),
        ) {
            itemsIndexed(documentService.listOfDocuments()) { index, item ->
                Column(
                    modifier = Modifier,
                ) {
                    DocumentListing(item.id, navController)
                }
            }

        }
        if (addActionState.value) {
            navController.navigate(
                "${Routes.DocumentEdit.name}/0"
            )
            addActionState.value = false
        }
    }

}

@Composable
fun DocumentListing(index: Long, navController: NavController) {

    val document = documentService.get(index)

    Row(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp, start = 8.dp, end = 8.dp)
            .fillMaxWidth()
            .clickable {
                navController.navigate("${Routes.DocumentPreview.name}/${document.id}")
            },
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Column {
            Text(text = document.symbol)

            Text(text = contractorService.get(document.contractor).name)
        }

        Text(text = document.date.toString())

    }
    Row {
        Divider(color = MaterialTheme.colorScheme.primary)
    }
}

//index = 0 -> add, index >= 1 ->update
@Composable
fun DocumentEdit(
    index: Long?,
    navController: NavController,
) {
    var document: Document
    if (index == 0L) {
        document = Document()
    }
    else
    {
        document = documentService.get(index!!)
    }
    val context = LocalContext.current
    Column(
        modifier = Modifier
            .padding(horizontal = 20.dp, vertical = 40.dp)
            .fillMaxSize(),
        verticalArrangement = Arrangement.Top,
        horizontalAlignment = Alignment.Start
    ) {
        var symbol by remember { mutableStateOf(document.symbol) }

        OutlinedTextField(
            value = symbol,
            onValueChange = { symbol = it },
            label = { Text("Symbol") },
        )

        Spacer(modifier = Modifier.height(20.dp))

        var date by remember { mutableStateOf(document.date.toString()) }
        OutlinedTextField(
            value = date,
            onValueChange = {
                date = it
            },
            label = { Text("Data") },
            supportingText = { Text("YYYY-MM-DD") },

            )

        var contractorId by remember { mutableStateOf(document.contractor) }
        Row {
            contractorId = searchableExposedDropdownMenuBox(document)
            Button(
                onClick = {

                    navController.navigate(
                        "${Routes.ContractorEdit.name}/${0}"
                    )

                },
                modifier = Modifier
                    .padding(vertical = 12.dp, horizontal = 4.dp)
            ) {
                Icon(
                    Icons.Rounded.Add,
                    contentDescription = stringResource(id = R.string.untitled)
                )
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        ElevatedButton(onClick = {

            try {
                val parsedDate = LocalDate.parse(date)
                document.update(symbol, parsedDate, contractorId)
                if(index == 0L){
                    documentService.addDocument(document)
                }else{
                    documentService.updateDocument(document)
                }
                navController.popBackStack()
            } catch (e: DateTimeParseException) {
                Toast.makeText(context, "Niepoprawna data", Toast.LENGTH_SHORT).show()
            }

        }) {
            Text(text = "Confirm")
        }
        BackHandler() {
            navController.popBackStack()
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun searchableExposedDropdownMenuBox(document: Document): Long {
    val currentContractorList = contractorService.listOfContractors()

    var expanded by remember { mutableStateOf(false) }
    var selectedText by remember { mutableStateOf("") }

    var contractorId: Long by remember { mutableStateOf(document.contractor) }

    Box(
        modifier = Modifier
            .padding(vertical = 12.dp)
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
                label = { Text(text = "Kontrahent") },
                readOnly = true,
                trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded) },
                modifier = Modifier.menuAnchor()
            )
            ExposedDropdownMenu(
                expanded = expanded,
                onDismissRequest = {}
            ) {
                currentContractorList.forEachIndexed() { index, item ->
                    DropdownMenuItem(
                        text = { Text(text = "${item.name}, ${item.symbol}") },
                        onClick = {
                            selectedText = "${item.name}, ${item.symbol}"
                            expanded = false
                            contractorId = item.id
                        }
                    )
                }
            }

        }
    }

    return contractorId
}

