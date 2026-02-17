package com.zappyware.moviebrowser.common.ui

data class ViewState<out T>(
    val status: Status,
    val data: T?,
    val message: String?)
{
    companion object {
        fun <T> loading() = ViewState(Status.LOADING, null, null)
        fun <T> success(data: T?) = ViewState(Status.SUCCESS, data, null)
        fun <T> error(msg: String) = ViewState(Status.ERROR, null, msg)
    }
}
