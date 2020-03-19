package com.davidmendozamartinez.clean.architecture.sample.presentation

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.davidmendozamartinez.clean.architecture.sample.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainPresenter.View {

    private lateinit var mainPresenter: MainPresenter

    private val locationsAdapter =
        LocationsAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        recycler.adapter = locationsAdapter

        mainPresenter =
            MainPresenter(
                this
            )

        newLocationBtn.setOnClickListener {
            mainPresenter.onLocationButtonClicked()
        }
    }

    override fun updateItems(locations: List<PresentationLocation>) {
        locationsAdapter.items = locations
    }

    override fun onDestroy() {
        mainPresenter.onDestroy()
        super.onDestroy()
    }
}