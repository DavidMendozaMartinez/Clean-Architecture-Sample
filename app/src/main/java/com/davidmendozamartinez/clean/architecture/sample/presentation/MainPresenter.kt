package com.davidmendozamartinez.clean.architecture.sample.presentation

import com.davidmendozamartinez.clean.architecture.sample.usecases.GetLocations
import com.davidmendozamartinez.clean.architecture.sample.usecases.RequestNewLocation
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(
    private var view: View?,
    private val getLocations: GetLocations,
    private val requestNewLocation: RequestNewLocation
) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<PresentationLocation>)
    }

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun onCreate() {
        launch {
            val locations = withContext(Dispatchers.IO) { getLocations.invoke() }
            view?.updateItems(locations.map { it.toPresentationLocation() })
        }
    }

    fun onLocationButtonClicked() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewLocation.invoke() }
            view?.updateItems(newLocations.map { it.toPresentationLocation() })
        }
    }

    fun onDestroy() {
        job.cancel()
    }
}