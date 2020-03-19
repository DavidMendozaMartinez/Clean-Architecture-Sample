package com.davidmendozamartinez.clean.architecture.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidmendozamartinez.clean.architecture.sample.R
import kotlinx.android.synthetic.main.activity_main.*
import org.koin.android.scope.currentScope
import org.koin.core.parameter.parametersOf

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private val mainPresenter: MainPresenter
            by currentScope.inject { parametersOf(this) }

    private val locationsAdapter = LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        newLocationBtn.setOnClickListener {
            mainPresenter.onLocationButtonClicked()
        }

        mainPresenter.onCreate()
    }

    override fun updateItems(locations: List<PresentationLocation>) {
        locationsAdapter.items = locations
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}