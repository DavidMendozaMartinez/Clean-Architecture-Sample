package com.davidmendozamartinez.clean.architecture.sample.data.repository

import com.davidmendozamartinez.clean.architecture.sample.data.datasource.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.data.datasource.PersistenceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location

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