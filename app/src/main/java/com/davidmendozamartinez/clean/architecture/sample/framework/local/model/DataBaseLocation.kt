package com.davidmendozamartinez.clean.architecture.sample.framework.local.model

import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import java.util.*

data class DataBaseLocation(val latitude: Double, val longitude: Double, val date: Date)

fun Location.mapToFramework(): DataBaseLocation =
    DataBaseLocation(
        latitude,
        longitude,
        date
    )

fun DataBaseLocation.mapToDomain(): Location =
    Location(
        latitude,
        longitude,
        date
    )