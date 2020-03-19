package com.davidmendozamartinez.clean.architecture.sample.presentation.locations

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidmendozamartinez.clean.architecture.sample.R
import com.davidmendozamartinez.clean.architecture.sample.presentation.model.PresentationLocation
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf

class LocationsActivity : AppCompatActivity(),
    LocationsPresenter.View {

    private val locationsPresenter: LocationsPresenter
            by currentScope.inject { parametersOf(this) }

    private val locationsAdapter = LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener {
            locationsPresenter.onLocationButtonClicked()
        }

        locationsPresenter.onCreate()
    }

    override fun updateItems(locations: List<PresentationLocation>) {
        locationsAdapter.items = locations
    }

    override fun onDestroy() {
        locationsPresenter.onDestroy()
        super.onDestroy()
    }
}