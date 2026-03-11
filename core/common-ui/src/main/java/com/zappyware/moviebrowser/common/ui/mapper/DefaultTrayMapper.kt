package com.zappyware.moviebrowser.common.ui.mapper

import androidx.compose.runtime.Composable
import com.zappyware.moviebrowser.common.ui.TrayMapper
import com.zappyware.moviebrowser.common.ui.trays.HorizontalPagerTrayWidgetComposable
import com.zappyware.moviebrowser.common.ui.trays.ShowcaseTrayWidgetComposable
import com.zappyware.moviebrowser.data.tray.HorizontalPagerTrayWidget
import com.zappyware.moviebrowser.data.tray.ShowcaseTrayWidget
import com.zappyware.moviebrowser.data.tray.TrayWidget
import com.zappyware.moviebrowser.data.widget.Widget
import javax.inject.Inject

class DefaultTrayMapper @Inject constructor() : TrayMapper {
    @Composable
    override fun Map(tray: TrayWidget, onDetailsClicked: (Widget) -> Unit) {
        when (tray) {
            is HorizontalPagerTrayWidget -> {
                HorizontalPagerTrayWidgetComposable(
                    tray = tray,
                    onDetailsClicked = onDetailsClicked,
                )
            }
            is ShowcaseTrayWidget -> {
                ShowcaseTrayWidgetComposable(
                    tray = tray,
                    onDetailsClicked = onDetailsClicked,
                )
            }
        }
    }
}
