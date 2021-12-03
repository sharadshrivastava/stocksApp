package com.test.app.ui.stocks

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.test.app.data.network.Resource
import com.test.app.domain.models.ui.StocksItem
import com.test.app.domain.models.ui.ViewState
import com.test.app.domain.usecases.StocksUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class StocksViewModel @Inject constructor(
    private val useCase: StocksUseCase
) : ViewModel() {


    val viewState = MutableLiveData<ViewState>()

    val stocks = liveData {
        emit(Resource.loading())
        emit(useCase.stocks())
    }

    init {
        viewState.value = ViewState()
    }

    fun manageViewStates(resource: Resource<List<StocksItem?>?>) {
        when (resource.status) {
            Resource.Status.LOADING -> viewState.value = currentViewState().copy(loading = true)
            Resource.Status.SUCCESS -> manageViewStates(isSuccess = true)
            Resource.Status.ERROR -> manageViewStates(msg = resource.message)
        }
    }

    private fun manageViewStates(
        isSuccess: Boolean = false,
        msg: String? = null
    ) {
        if (isSuccess) {
            viewState.value =
                currentViewState().copy(loading = false, empty = false, error = false)
        } else {
            viewState.value =
                currentViewState().copy(loading = false, empty = true, error = true, errorMsg = msg)
        }
    }

    private fun currentViewState() = viewState.value!!
}