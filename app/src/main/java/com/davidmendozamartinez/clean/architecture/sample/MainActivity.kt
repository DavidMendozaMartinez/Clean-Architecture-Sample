package com.davidmendozamartinez.clean.architecture.sample

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.*
import java.util.*
import kotlin.coroutines.CoroutineContext

class MainActivity : AppCompatActivity(), CoroutineScope {

    private lateinit var job: Job

    override val coroutineContext: CoroutineContext
        get() = job + Dispatchers.Main

    private val random = Random(System.currentTimeMillis())

    private var savedLocations = emptyList<Location>()

    private val locationsAdapter = LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        job = SupervisorJob()

        recycler.adapter = locationsAdapter

        launch {
            locationsAdapter.items = withContext(Dispatchers.IO) { savedLocations }
        }

        newLocationBtn.setOnClickListener {
            launch {
                val newLocations = withContext(Dispatchers.IO) { requestNewLocation() }
                locationsAdapter.items = newLocations
            }
        }
    }

    private fun requestNewLocation(): List<Location> {
        val newLocation = getDeviceLocation()
        savedLocations = savedLocations + newLocation
        return savedLocations
    }

    private fun getDeviceLocation(): Location =
        Location(random.nextDouble() * 180 - 90, random.nextDouble() * 360 - 180, Date())

    override fun onDestroy() {
        job.cancel()
        super.onDestroy()
    }
}