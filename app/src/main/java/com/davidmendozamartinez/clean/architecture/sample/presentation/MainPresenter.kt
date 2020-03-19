package com.davidmendozamartinez.clean.architecture.sample.presentation

import com.davidmendozamartinez.clean.architecture.sample.data.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.framework.DeviceLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.framework.InMemoryLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.usecases.GetLocations
import com.davidmendozamartinez.clean.architecture.sample.usecases.RequestNewLocation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: View) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<PresentationLocation>)
    }

    private var job: Job = SupervisorJob()

    private val locationRepository =
        LocationRepository(DeviceLocationDataSourceImpl(), InMemoryLocationDataSourceImpl())
    private val requestNewLocation = RequestNewLocation(locationRepository)
    private val getLocations = GetLocations(locationRepository)

    init {
        launch {
            val locations = withContext(Dispatchers.IO) { getLocations.invoke() }
            this@MainPresenter.view.updateItems(locations.map { it.toPresentationLocation() })
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun onLocationButtonClicked() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewLocation.invoke() }
            view.updateItems(newLocations.map { it.toPresentationLocation() })
        }
    }

    fun onDestroy() {
        job.cancel()
    }
}