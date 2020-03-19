package com.davidmendozamartinez.clean.architecture.sample

import com.davidmendozamartinez.clean.architecture.sample.data.datasource.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.data.datasource.PersistenceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.data.repository.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.framework.device.datasource.DeviceLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.framework.local.datasource.InMemoryLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.presentation.locations.LocationsActivity
import com.davidmendozamartinez.clean.architecture.sample.presentation.locations.LocationsPresenter
import com.davidmendozamartinez.clean.architecture.sample.usecases.location.GetLocationsUseCase
import com.davidmendozamartinez.clean.architecture.sample.usecases.location.RequestNewLocationUseCase
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<PersistenceLocationDataSource> { InMemoryLocationDataSourceImpl() }
    single<DeviceLocationDataSource> { DeviceLocationDataSourceImpl() }
}

val useCaseModule = module {
    factory {
        GetLocationsUseCase(
            get()
        )
    }
    factory {
        RequestNewLocationUseCase(
            get()
        )
    }
}

val dataModule = module {
    factory {
        LocationRepository(
            get(),
            get()
        )
    }
}

val presentersModule = module {
    scope(named<LocationsActivity>()) {
        scoped { (view: LocationsPresenter.View) ->
            LocationsPresenter(
                view,
                get(),
                get()
            )
        }
    }
}