package com.zappyware.moviebrowser

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.zappyware.moviebrowser.page.detail.MovieDetailsScreen
import com.zappyware.moviebrowser.page.landing.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)

        setContent {
            MaterialTheme(
                colorScheme = if(isSystemInDarkTheme()) {
                    darkColorScheme(onSurface = Color.White)
                } else {
                    lightColorScheme()
                }
            ) {
                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = "list") {
                    composable("list") {
                        MovieListScreen(
                            viewModel = hiltViewModel(),
                            onDetailsClicked = { selectedMovie ->
                                navController.navigate("details/${selectedMovie.id}")
                            },
                        )
                    }
                    composable(
                        "details/{movieId}",
                        arguments = listOf(
                            navArgument("movieId") {
                                type = NavType.LongType
                            }
                        )
                    ) {
                        val movieId = it.arguments?.getLong("movieId") ?: 0L
                        MovieDetailsScreen(
                            viewModel = hiltViewModel(),
                            movieId = movieId,
                        )
                    }
                }
            }
        }
    }
}
