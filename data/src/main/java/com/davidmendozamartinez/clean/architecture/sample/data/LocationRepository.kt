package com.davidmendozamartinez.clean.architecture.sample.data

import com.davidmendozamartinez.clean.architecture.sample.domain.Location

class LocationRepository(
    private val deviceLocationDataSource: DeviceLocationDataSource,
    private val persistenceLocationDataSource: PersistenceLocationDataSource
) {

    fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationDataSource.getDeviceLocation()
        persistenceLocationDataSource.saveNewLocation(newLocation)
        return persistenceLocationDataSource.getPersistedLocations()
    }

    fun getSavedLocations(): List<Location> = persistenceLocationDataSource.getPersistedLocations()
}

interface DeviceLocationDataSource {
    fun getDeviceLocation(): Location
}

interface PersistenceLocationDataSource {
    fun getPersistedLocations(): List<Location>
    fun saveNewLocation(location: Location)
}