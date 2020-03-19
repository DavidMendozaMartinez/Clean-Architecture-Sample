package com.davidmendozamartinez.clean.architecture.sample.presentation.model

import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import com.davidmendozamartinez.clean.architecture.sample.presentation.toPrettifiedString

data class PresentationLocation(val coordinates: String, val date: String)

fun Location.mapToPresentation(): PresentationLocation =
    PresentationLocation(
        "${latitude.toPrettifiedString()} | ${longitude.toPrettifiedString()}",
        date.toPrettifiedString()
    )