package sideproject.mercy.presentation.ui.main.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import sideproject.mercy.domain.getOrDefault
import sideproject.mercy.domain.github.GetContributorsUseCase
import sideproject.mercy.presentation.base.ui.BaseViewModel
import sideproject.mercy.shared.model.User
import sideproject.mercy.domain.Result
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getContributorsUseCase: GetContributorsUseCase
): BaseViewModel() {

    private val _contributors = MutableLiveData<List<User>>(emptyList())
    val contributors: LiveData<List<User>> = _contributors

    fun getContributors() {
        viewModelScope.launch {
            val result = getContributorsUseCase(
                GetContributorsUseCase.Param(
                    owner = "LeeYoonSam",
                    name = "BasicAndroidSample",
                    pageNo = 1
                )
            )

            when (result) {
                is Result.Success -> {
                    _contributors.value = result.getOrDefault(emptyList())
                    hideLoading()
                }

                is Result.Error -> {
                    _contributors.value = emptyList()
                    hideLoading()
                }

                is Result.Loading -> {
                    showLoading()
                }
            }
        }
    }
}