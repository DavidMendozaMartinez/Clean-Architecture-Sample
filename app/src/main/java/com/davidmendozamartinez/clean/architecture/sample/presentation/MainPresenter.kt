package com.davidmendozamartinez.clean.architecture.sample.presentation

import com.davidmendozamartinez.clean.architecture.sample.data.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.domain.Location
import com.davidmendozamartinez.clean.architecture.sample.framework.DeviceLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.framework.InMemoryLocationDataSourceImpl
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: View) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<Location>)
    }

    private var job: Job = SupervisorJob()
    private val locationRepository =
        LocationRepository(DeviceLocationDataSourceImpl(), InMemoryLocationDataSourceImpl())

    init {
        launch {
            val locations = withContext(Dispatchers.IO) {
                locationRepository.getSavedLocations()
            }
            this@MainPresenter.view.updateItems(locations)
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun onLocationButtonClicked() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewLocation() }
            view.updateItems(newLocations)
        }
    }

    private fun requestNewLocation(): List<Location> = locationRepository.requestNewLocation()

    fun onDestroy() {
        job.cancel()
    }
}