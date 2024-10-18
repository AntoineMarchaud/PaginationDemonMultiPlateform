package com.amarchaud.paginationdemokmm

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.amarchaud.ui.screen.detail.UserDetailComposable
import com.amarchaud.ui.screen.mainList.MainListComposable
import kotlinx.serialization.Serializable

object MultiNav {
    @Serializable
    object Home

    @Serializable
    data class Detail(val id: Long)
}

@Composable
fun App() {
    val navController = rememberNavController()
    NavHost(
        navController = navController,
        startDestination = MultiNav.Home,
    ) {
        composable<MultiNav.Home> {
            MainListComposable(
                onUserClick = {
                    navController.navigate(MultiNav.Detail(it))
                }
            )
        }

        composable<MultiNav.Detail> {
            UserDetailComposable(
                onBack = {
                    navController.popBackStack()
                }
            )
        }
    }
}