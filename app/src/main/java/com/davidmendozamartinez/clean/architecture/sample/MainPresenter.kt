package com.davidmendozamartinez.clean.architecture.sample

import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainPresenter(private val view: View) : CoroutineScope {

    interface View {
        fun updateItems(locations: List<Location>)
    }

    private var job: Job = SupervisorJob()

    init {
        launch {
            this@MainPresenter.view.updateItems(withContext(Dispatchers.IO) { savedLocations })
        }
    }

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val random = Random(System.currentTimeMillis())

    private var savedLocations = emptyList<Location>()

    fun onLocationButtonClicked() {
        launch {
            val newLocations = withContext(Dispatchers.IO) { requestNewLocation() }
            view.updateItems(newLocations)
        }
    }

    private fun requestNewLocation(): List<Location> {
        val newLocation = getDeviceLocation()
        savedLocations = savedLocations + newLocation
        return savedLocations
    }

    private fun getDeviceLocation(): Location =
        Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())

    fun onDestroy() {
        job.cancel()
    }
}