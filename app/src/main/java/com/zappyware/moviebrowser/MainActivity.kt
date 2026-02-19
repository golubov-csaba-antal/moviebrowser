package com.zappyware.moviebrowser

import android.os.Bundle
import androidx.activity.SystemBarStyle
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.core.view.WindowCompat
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import androidx.lifecycle.viewmodel.navigation3.rememberViewModelStoreNavEntryDecorator
import androidx.navigation3.runtime.entryProvider
import androidx.navigation3.runtime.rememberSaveableStateHolderNavEntryDecorator
import androidx.navigation3.ui.NavDisplay
import com.zappyware.moviebrowser.composable.Toolbar
import com.zappyware.moviebrowser.navigation.Details
import com.zappyware.moviebrowser.navigation.Landing
import com.zappyware.moviebrowser.page.detail.MovieDetailsScreen
import com.zappyware.moviebrowser.page.landing.MovieListScreen
import dagger.hilt.android.AndroidEntryPoint
import kotlin.collections.listOf

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        WindowCompat.enableEdgeToEdge(window)

        setContent {
            enableEdgeToEdge(
                statusBarStyle = SystemBarStyle.light(
                    Color.Transparent.value.toInt(), Color.Transparent.value.toInt()
                ),
                navigationBarStyle = SystemBarStyle.light(
                    Color.Transparent.value.toInt(), Color.Transparent.value.toInt()
                )
            )
            MaterialTheme(
                colorScheme = if(isSystemInDarkTheme()) {
                    darkColorScheme(onSurface = Color.White)
                } else {
                    lightColorScheme()
                }
            ) {
                val backStack = remember { mutableStateListOf<Any>(Landing) }
                Scaffold(
                    topBar = {
                        Toolbar(
                            backStack = backStack,
                            title = "Movies",
                            canGoBack = backStack.size > 1)
                    }
                ) { innerPadding ->
                    NavDisplay(
                        modifier = Modifier.padding(innerPadding),
                        backStack = backStack,
                        onBack = { backStack.removeLastOrNull() },
                        entryDecorators = listOf(
                            rememberSaveableStateHolderNavEntryDecorator(),
                            rememberViewModelStoreNavEntryDecorator(),
                        ),
                        entryProvider = entryProvider {
                            entry<Landing> {
                                MovieListScreen(
                                    viewModel = hiltViewModel(),
                                    onDetailsClicked = { selectedMovie ->
                                        backStack.add(Details(selectedMovie.id))
                                    },
                                )
                            }
                            entry<Details> {
                                MovieDetailsScreen(
                                    viewModel = hiltViewModel(),
                                    movieId = it.movieId,
                                )
                            }
                        },
                        //transitionSpec = { slideInHorizontally(initialOffsetX = { it }) togetherWith slideOutHorizontally(targetOffsetX = { -it }) },
                        //popTransitionSpec = { slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it }) },
                        //predictivePopTransitionSpec = { slideInHorizontally(initialOffsetX = { -it }) togetherWith slideOutHorizontally(targetOffsetX = { it }) },
                    )
                }
            }
        }
    }
}
