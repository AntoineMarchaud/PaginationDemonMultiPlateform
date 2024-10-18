package com.amarchaud.paginationdemokmm

import com.amarchaud.data.di.daoModule
import com.amarchaud.data.di.networkModule
import com.amarchaud.data.di.repoModule
import com.amarchaud.data.di.useCaseModule
import com.amarchaud.ui.di.viewModelModule
import org.koin.core.context.startKoin

fun initKoin(){
    startKoin {
        modules(networkModule, daoModule, repoModule, useCaseModule, viewModelModule)
    }
}