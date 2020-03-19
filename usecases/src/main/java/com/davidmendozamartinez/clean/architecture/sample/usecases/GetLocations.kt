package com.davidmendozamartinez.clean.architecture.sample.usecases

import com.davidmendozamartinez.clean.architecture.sample.data.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.domain.Location

class GetLocations(private val locationRepository: LocationRepository) {

    fun invoke(): List<Location> = locationRepository.getSavedLocations()
}