package com.example.warehouseOrderApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.slideInHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.room.Room
import com.example.warehouseOrderApp.screens.ContractorEdit
import com.example.warehouseOrderApp.screens.ContractorList
import com.example.warehouseOrderApp.screens.DocumentEdit
import com.example.warehouseOrderApp.screens.DocumentList
import com.example.warehouseOrderApp.screens.DocumentPreview
import com.example.warehouseOrderApp.screens.EntryEdit
import com.example.warehouseOrderApp.screens.EntryPreview
import com.example.warehouseOrderApp.screens.MainMenu
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.room.AppDatabase

class MainActivity : ComponentActivity() {
    companion object {
        lateinit var database: AppDatabase
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        database = AppDatabase.getDatabase(applicationContext)
        setContent {
            NavHostBuilder()
        }
    }
}

@Composable
fun NavHostBuilder() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.MainMenu.name,


        enterTransition = {
            slideInHorizontally (animationSpec = spring(stiffness = Spring.StiffnessLow)){ it->it }
        },
        popEnterTransition = {
            slideInHorizontally (animationSpec = spring(stiffness = Spring.StiffnessLow)) { it -> -it }
        }
    ) {
        composable(route = Routes.MainMenu.name) {
            MainMenu(navController)
        }
        composable(route = Routes.Contractors.name) {
            ContractorList(navController)
        }
        composable(route = Routes.DocumentList.name) {
            DocumentList(navController)
        }
        composable(
            route = "${Routes.ContractorEdit.name}/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.LongType
            })
        ) {
            val index = it.arguments?.getLong("index")
            ContractorEdit(index!!, navController)
        }
        composable(route = "${Routes.DocumentEdit.name}/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.LongType
            })
        ) {
            val index = it.arguments?.getLong("index")
            DocumentEdit(index, navController)
        }
        composable(route = "${Routes.DocumentPreview.name}/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.LongType
            })
        ) {
            val index = it.arguments?.getLong("index")
            DocumentPreview(index!!, navController)
        }
        composable(route = "${Routes.EntryEdit.name}/{index}/{entryIndex}",
            arguments = listOf(navArgument("index") {
                type = NavType.LongType
            },
            navArgument("entryIndex"){
                type = NavType.LongType
            })
        ) {
            val index = it.arguments?.getLong("index")
            val entryIndex = it.arguments?.getLong("entryIndex")
            EntryEdit(index!!, entryIndex, navController)
        }

        composable(route = "${Routes.EntryPreview.name}/{index}/{entryIndex}",
            arguments = listOf(navArgument("index") {
                type = NavType.LongType
            },
                navArgument("entryIndex"){
                    type = NavType.LongType
                })
        ) {
            val index = it.arguments?.getInt("index")
            val entryIndex = it.arguments?.getInt("entryIndex")
            EntryPreview(index!!, entryIndex!!, navController)
        }
    }
}

