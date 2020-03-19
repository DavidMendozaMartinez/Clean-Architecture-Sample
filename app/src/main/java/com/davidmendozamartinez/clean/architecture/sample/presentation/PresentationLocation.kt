package com.davidmendozamartinez.clean.architecture.sample.presentation

import com.davidmendozamartinez.clean.architecture.sample.domain.Location

data class PresentationLocation(val coordinates: String, val date: String)

fun Location.toPresentationLocation(): PresentationLocation =
    PresentationLocation(
        "${latitude.toPrettifiedString()} | ${longitude.toPrettifiedString()}",
        date.toPrettifiedString()
    )