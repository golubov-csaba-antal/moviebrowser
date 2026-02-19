@file:OptIn(ExperimentalMaterial3Api::class)

package com.zappyware.moviebrowser.composable

import androidx.compose.foundation.background
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.sp
import com.zappyware.moviebrowser.common.ui.R

@Composable
fun Toolbar(
    backStack: SnapshotStateList<Any>,
    title: String,
    canGoBack: Boolean,
) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primaryContainer,
            titleContentColor = MaterialTheme.colorScheme.primary,
        ),
        title = {
            Text(
                text = title,
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 18.sp
            )
        },
        modifier = Modifier.background(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            if (canGoBack) {
                IconButton(
                    onClick = {
                        backStack.removeLastOrNull()
                    }
                ) {
                    Icon(painterResource(R.drawable.ic_navigate_back), "Backward navigation icon")
                }
            }
        },
        scrollBehavior = TopAppBarDefaults.pinnedScrollBehavior { true }
    )
}
