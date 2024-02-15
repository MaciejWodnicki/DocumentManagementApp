package com.example.warehouseOrderApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.warehouseOrderApp.screens.ContractorEdit
import com.example.warehouseOrderApp.screens.ContractorList
import com.example.warehouseOrderApp.screens.DocumentList
import com.example.warehouseOrderApp.screens.MainMenu
import com.example.warehouseOrderApp.src.data.Contractor
import com.example.warehouseOrderApp.src.data.Routes
import com.example.warehouseOrderApp.src.data.fromString
import java.lang.reflect.Type

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            NavHostBuilder()
        }
    }
}

@Composable
fun NavHostBuilder(){
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = Routes.MainMenu.name
    ) {
        composable(route = Routes.MainMenu.name){
            MainMenu(navController)
        }
        composable(route = Routes.Contractors.name){
            ContractorList(navController)
        }
        composable(route = Routes.DocumentList.name){
            DocumentList(navController)
        }
        composable(route = "${Routes.ContractorEdit.name}?contractor={contractor}",
            arguments = listOf(navArgument("contractor"){
                type = NavType.StringType
            })
        ){
            val arg = it.arguments?.getString("contractor")
            val contractor = fromString(arg)
            ContractorEdit(contractor = contractor, navController)
        }
        composable(route = Routes.DocumentEdit.name){

        }
    }
}

