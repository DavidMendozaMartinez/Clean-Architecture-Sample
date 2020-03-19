package com.davidmendozamartinez.clean.architecture.sample.framework

import com.davidmendozamartinez.clean.architecture.sample.presentation.Location

class InMemoryLocationDataSource {

    private var savedLocations = emptyList<Location>()

    fun getPersistedLocations(): List<Location> = savedLocations

    fun saveNewLocation(location: Location) {
        savedLocations = savedLocations + location
    }
}