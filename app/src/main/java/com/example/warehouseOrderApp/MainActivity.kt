package com.example.warehouseOrderApp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.EnterTransition
import androidx.compose.animation.ExitTransition
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.DurationBasedAnimationSpec
import androidx.compose.animation.core.FiniteAnimationSpec
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.IntOffset
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.warehouseOrderApp.screens.ContractorEdit
import com.example.warehouseOrderApp.screens.ContractorList
import com.example.warehouseOrderApp.screens.DocumentEdit
import com.example.warehouseOrderApp.screens.DocumentList
import com.example.warehouseOrderApp.screens.MainMenu
import com.example.warehouseOrderApp.src.data.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
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
                type = NavType.IntType
            })
        ) {
            val index = it.arguments?.getInt("index")
            ContractorEdit(index, navController)
        }
        composable(route = "${Routes.DocumentEdit.name}/{index}",
            arguments = listOf(navArgument("index") {
                type = NavType.IntType
            })
        ) {
            val index = it.arguments?.getInt("index")
            DocumentEdit(index, navController)
        }
    }
}

