package io.bitbot.bemusedbaboon.landing.home

import io.bitbot.bemusedbaboon.landing.home.view.HomeView
import io.bitbot.bemusedbaboon.common.viewmodel.BaseViewModel

class HomeViewModel(/* add use cases providers, etc. */) : BaseViewModel<HomeState, HomeViewModel>(
    model = HomeState(),
    view = { HomeView() }
) {
    init {
        // run and observe use cases
    }
}