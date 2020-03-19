package com.davidmendozamartinez.clean.architecture.sample.framework.device.datasource

import com.davidmendozamartinez.clean.architecture.sample.data.datasource.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import com.davidmendozamartinez.clean.architecture.sample.framework.device.model.DeviceLocation
import com.davidmendozamartinez.clean.architecture.sample.framework.device.model.mapToDomain
import java.util.*

class DeviceLocationDataSourceImpl : DeviceLocationDataSource {

    private val random = Random(System.currentTimeMillis())

    override fun getDeviceLocation(): Location =
        DeviceLocation(
            random.nextDouble() * 180 - 90,
            random.nextDouble() * 360 - 180,
            Date()
        ).mapToDomain()
}