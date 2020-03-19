package com.davidmendozamartinez.clean.architecture.sample

import com.davidmendozamartinez.clean.architecture.sample.data.DeviceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.data.LocationRepository
import com.davidmendozamartinez.clean.architecture.sample.data.PersistenceLocationDataSource
import com.davidmendozamartinez.clean.architecture.sample.framework.DeviceLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.framework.InMemoryLocationDataSourceImpl
import com.davidmendozamartinez.clean.architecture.sample.presentation.MainActivity
import com.davidmendozamartinez.clean.architecture.sample.presentation.MainPresenter
import com.davidmendozamartinez.clean.architecture.sample.usecases.GetLocations
import com.davidmendozamartinez.clean.architecture.sample.usecases.RequestNewLocation
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single<PersistenceLocationDataSource> { InMemoryLocationDataSourceImpl() }
    single<DeviceLocationDataSource> { DeviceLocationDataSourceImpl() }
}

val useCaseModule = module {
    factory { GetLocations(get()) }
    factory { RequestNewLocation(get()) }
}

val dataModule = module {
    factory { LocationRepository(get(), get()) }
}

val presentersModule = module {
    scope(named<MainActivity>()) {
        scoped { (view: MainPresenter.View) -> MainPresenter(view, get(), get()) }
    }
}