package com.davidmendozamartinez.clean.architecture.sample.framework.device.model

import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import java.util.*

data class DeviceLocation(val latitude: Double, val longitude: Double, val date: Date)

fun DeviceLocation.mapToDomain(): Location =
    Location(
        latitude,
        longitude,
        date
    )