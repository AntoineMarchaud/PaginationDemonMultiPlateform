package com.amarchaud.ui.di

import com.amarchaud.ui.screen.detail.UserDetailViewModel
import com.amarchaud.ui.screen.mainList.MainListViewModel
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MainListViewModel(
            getRandomUsersWithRoomUseCase = get(),
        )
    }

    viewModel {
        UserDetailViewModel(
            stateHandle = get(),
            getUserFromCacheUseCase = get()
        )
    }
}