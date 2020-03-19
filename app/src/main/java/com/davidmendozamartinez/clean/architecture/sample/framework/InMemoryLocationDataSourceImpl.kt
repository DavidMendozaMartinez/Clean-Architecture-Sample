package com.davidmendozamartinez.clean.architecture.sample.framework

import com.davidmendozamartinez.clean.architecture.sample.data.PersistenceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.domain.Location
import java.util.*

class InMemoryLocationDataSourceImpl : PersistenceLocationDataSource {

    private var savedLocations = emptyList<DataBaseLocation>()

    override fun getPersistedLocations(): List<Location> =
        savedLocations.map { it.toDomainLocation() }

    override fun saveNewLocation(location: Location) {
        savedLocations = savedLocations + location.toDataBaseLocation()
    }
}

data class DataBaseLocation(val latitude: Double, val longitude: Double, val date: Date)

fun Location.toDataBaseLocation(): DataBaseLocation = DataBaseLocation(latitude, longitude, date)
fun DataBaseLocation.toDomainLocation(): Location = Location(latitude, longitude, date)