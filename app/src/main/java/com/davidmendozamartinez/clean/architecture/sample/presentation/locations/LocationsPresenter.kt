package com.davidmendozamartinez.clean.architecture.sample.presentation.locations

import com.davidmendozamartinez.clean.architecture.sample.presentation.model.PresentationLocation
import com.davidmendozamartinez.clean.architecture.sample.presentation.model.mapToPresentation
import com.davidmendozamartinez.clean.architecture.sample.usecases.location.GetLocationsUseCase
import com.davidmendozamartinez.clean.architecture.sample.usecases.location.RequestNewLocationUseCase
import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

class LocationsPresenter(
    private var view: View?,
    private val getLocationsUseCase: GetLocationsUseCase,
    private val requestNewLocationUseCase: RequestNewLocationUseCase
) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<PresentationLocation>)
    }

    private var job: Job = SupervisorJob()

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    fun onCreate() {
        launch {
            val locations = withContext(Dispatchers.IO) { getLocationsUseCase.invoke() }
            view?.updateItems(locations.map { it.mapToPresentation() })
        }
    }

    fun onLocationButtonClicked() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewLocationUseCase.invoke() }
            view?.updateItems(newLocations.map { it.mapToPresentation() })
        }
    }

    fun onDestroy() {
        job.cancel()
    }
}