package com.davidmendozamartinez.clean.architecture.sample.presentation.locations

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.davidmendozamartinez.clean.architecture.sample.R
import com.davidmendozamartinez.clean.architecture.sample.presentation.inflate
import com.davidmendozamartinez.clean.architecture.sample.presentation.model.PresentationLocation
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.view_location_item.view.*
import kotlin.properties.Delegates

class LocationsAdapter : RecyclerView.Adapter<LocationsAdapter.ViewHolder>() {

    var items: List<PresentationLocation> by Delegates.observable(emptyList()) { _, _, _ ->
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder =
        ViewHolder(
            parent.inflate(R.layout.view_location_item)
        )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ViewHolder(override val containerView: View) : RecyclerView.ViewHolder(containerView),
        LayoutContainer {

        fun bind(presentationLocation: PresentationLocation) {
            with(presentationLocation) {
                itemView.locationCoordinates.text = coordinates
                itemView.locationDate.text = date
            }
        }
    }
}