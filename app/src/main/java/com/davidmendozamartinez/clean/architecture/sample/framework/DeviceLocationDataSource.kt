package com.davidmendozamartinez.clean.architecture.sample.framework

import com.davidmendozamartinez.clean.architecture.sample.presentation.Location
import java.util.*

class DeviceLocationDataSource {

    private val random = Random(System.currentTimeMillis())

    fun getDeviceLocation(): Location =
        Location(
            random.nextDouble() * 180 - 90,
            random.nextDouble() * 360 - 180,
            Date()
        )
}