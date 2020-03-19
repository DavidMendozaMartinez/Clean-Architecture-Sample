package com.davidmendozamartinez.clean.architecture.sample.data.datasource

import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location

interface DeviceLocationDataSource {
    fun getDeviceLocation(): Location
}

interface PersistenceLocationDataSource {
    fun getPersistedLocations(): List<Location>
    fun saveNewLocation(location: Location)
}