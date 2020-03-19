package com.davidmendozamartinez.clean.architecture.sample.framework

import com.davidmendozamartinez.clean.architecture.sample.data.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.domain.Location
import java.util.*

class DeviceLocationDataSourceImpl : DeviceLocationDataSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
        FrameworkLocation(
            random.nextDouble() * 180 - 90,
            random.nextDouble() * 360 - 180,
            Date()
        ).toDomainLocation()
}

data class FrameworkLocation(val latitude: Double, val longitude: Double, val date: Date)

fun FrameworkLocation.toDomainLocation(): Location = Location(latitude, longitude, date)