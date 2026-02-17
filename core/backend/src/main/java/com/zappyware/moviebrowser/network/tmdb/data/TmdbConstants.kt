package com.zappyware.moviebrowser.network.tmdb.data

const val BASE_URL = "https://api.themoviedb.org/3/"
const val AUTH = "Bearer eyJhbGciOiJIUzI1NiJ9.eyJhdWQiOiJjYTUxMGVhMTk2Mjg0NzI0Nzk1NzY4MTNlNTliMWI3ZCIsInN1YiI6IjY1Yjc5YTQ5YTBiNjkwMDE3YmNmNzBhOSIsInNjb3BlcyI6WyJhcGlfcmVhZCJdLCJ2ZXJzaW9uIjoxfQ.lRQLRVUlvEQ0Nu3In5Ys52oyI1wPfPZ_X7QeXHQH3ds"

fun smallCoverUrl(posterPath: String) = "https://image.tmdb.org/t/p/w200$posterPath"

fun coverUrl(posterPath: String) = "https://image.tmdb.org/t/p/original$posterPath"
