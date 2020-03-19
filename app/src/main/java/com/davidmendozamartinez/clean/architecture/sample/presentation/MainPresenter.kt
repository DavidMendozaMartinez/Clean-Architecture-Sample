package com.davidmendozamartinez.clean.architecture.sample.presentation

import com.davidmendozamartinez.clean.architecture.sample.framework.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.framework.InMemoryLocationDataSource
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: View) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<Location>)
    }

    private var job: Job = SupervisorJob()
    private val deviceLocationDataSource =
        DeviceLocationDataSource()
    private val inMemoryLocationDataSource =
        InMemoryLocationDataSource()

    init {
        launch {
            val locations = withContext(Dispatchers.IO) {
                inMemoryLocationDataSource.getPersistedLocations()
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

    private fun requestNewLocation(): List<Location> {
        val newLocation = deviceLocationDataSource.getDeviceLocation()
        inMemoryLocationDataSource.saveNewLocation(newLocation)
        return inMemoryLocationDataSource.getPersistedLocations()
    }

    fun onDestroy() {
        job.cancel()
    }
}