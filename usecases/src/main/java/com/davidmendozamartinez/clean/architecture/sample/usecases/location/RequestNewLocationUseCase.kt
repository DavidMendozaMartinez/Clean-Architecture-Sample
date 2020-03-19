package com.davidmendozamartinez.clean.architecture.sample.usecases.location

import com.davidmendozamartinez.clean.architecture.sample.data.repository.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.domain.model.Location
import com.davidmendozamartinez.clean.architecture.sample.usecases.UseCase

class RequestNewLocationUseCase(private val locationRepository: LocationRepository) :
    UseCase<List<Location>>() {

    override fun invoke(): List<Location> = locationRepository.requestNewLocation()
}