package com.davidmendozamartinez.clean.architecture.sample.framework.local.datasource

import com.davidmendozamartinez.clean.architecture.sample.data.datasource.PersistenceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import com.davidmendozamartinez.clean.architecture.sample.framework.local.model.DataBaseLocation
import com.davidmendozamartinez.clean.architecture.sample.framework.local.model.mapToDomain
import com.davidmendozamartinez.clean.architecture.sample.framework.local.model.mapToFramework

class InMemoryLocationDataSourceImpl : PersistenceLocationDataSource {

    private var savedLocations = emptyList<DataBaseLocation>()

    override fun getPersistedLocations(): List<Location> =
        savedLocations.map { it.mapToDomain() }

    override fun saveNewLocation(location: Location) {
        savedLocations = savedLocations + location.mapToFramework()
    }
}