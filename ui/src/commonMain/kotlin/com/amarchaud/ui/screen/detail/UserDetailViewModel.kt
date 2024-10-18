package com.amarchaud.ui.screen.detail

//import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.amarchaud.domain.usecase.GetOneUserUseCase
import com.amarchaud.ui.screen.detail.mappers.toDetailUiModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class UserDetailViewModel(
    stateHandle: SavedStateHandle,
    private val getUserFromCacheUseCase: GetOneUserUseCase
) : ViewModel() {

    private val localId =
        stateHandle.get<Long>("id") ?: throw NullPointerException("localId can't be null")

    private val _userDetailUiModel = MutableStateFlow(com.amarchaud.ui.screen.detail.models.UserDetailUiModel())
    val userDetailUiModel = _userDetailUiModel.asStateFlow()

    init {
        viewModelScope.launch {
            getUserFromCacheUseCase.run(localId)?.let { userModel ->
                _userDetailUiModel.update {
                    userModel.toDetailUiModel()
                }
            }
        }
    }
}