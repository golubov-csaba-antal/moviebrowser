package com.zappyware.moviebrowser.common.ui.mapper

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.zappyware.moviebrowser.common.ui.WidgetMapper
import com.zappyware.moviebrowser.common.ui.widgets.ImageWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetCircularComposable
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetLandscapeComposable
import com.zappyware.moviebrowser.common.ui.widgets.MovieWidgetPortraitComposable
import com.zappyware.moviebrowser.common.ui.widgets.PeopleWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.SeasonWidgetComposable
import com.zappyware.moviebrowser.common.ui.widgets.VideoWidgetComposable
import com.zappyware.moviebrowser.data.common.Orientation
import com.zappyware.moviebrowser.data.widget.ImageWidget
import com.zappyware.moviebrowser.data.widget.MovieWidget
import com.zappyware.moviebrowser.data.widget.PeopleWidget
import com.zappyware.moviebrowser.data.widget.SeasonWidget
import com.zappyware.moviebrowser.data.widget.VideoWidget
import com.zappyware.moviebrowser.data.widget.Widget
import javax.inject.Inject

class DefaultWidgetMapper @Inject constructor() : WidgetMapper {
    @Composable
    override fun Map(
        widget: Widget,
        orientation: Orientation,
        onDetailsClicked: (Widget) -> Unit,
        modifier: Modifier
    ) {
        when (widget) {
            is MovieWidget -> {
                when (orientation) {
                    Orientation.Portrait -> {
                        MovieWidgetPortraitComposable(
                            modifier = modifier,
                            widget = widget,
                            castShadow = true,
                            onDetailsClicked = onDetailsClicked,
                        )
                    }
                    Orientation.Landscape -> {
                        MovieWidgetLandscapeComposable(
                            modifier = modifier,
                            widget = widget,
                            castShadow = true,
                            onDetailsClicked = onDetailsClicked,
                        )
                    }
                    Orientation.Circular -> {
                        MovieWidgetCircularComposable(
                            modifier = modifier,
                            widget = widget,
                            castShadow = true,
                            onDetailsClicked = onDetailsClicked,
                        )
                    }
                }
            }
            is PeopleWidget -> {
                PeopleWidgetComposable(
                    modifier = modifier,
                    widget = widget,
                    castShadow = true,
                    onDetailsClicked = onDetailsClicked,
                )
            }
            is VideoWidget -> {
                VideoWidgetComposable(
                    modifier = modifier,
                    widget = widget,
                    castShadow = true,
                    onDetailsClicked = onDetailsClicked,
                )
            }
            is ImageWidget -> {
                ImageWidgetComposable(
                    modifier = modifier,
                    widget = widget,
                    castShadow = true,
                    onDetailsClicked = onDetailsClicked,
                )
            }
            is SeasonWidget -> {
                SeasonWidgetComposable(
                    modifier = modifier,
                    widget = widget,
                    castShadow = true,
                    onDetailsClicked = onDetailsClicked,
                )
            }
        }
    }
}
