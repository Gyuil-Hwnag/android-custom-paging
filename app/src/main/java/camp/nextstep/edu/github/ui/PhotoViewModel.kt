package camp.nextstep.edu.github.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.github.util.PAGE_SIZE
import camp.nextstep.edu.github.util.SingleLiveEvent
import com.nextstep.edu.domain.model.Photo
import com.nextstep.edu.domain.usecase.GetPhotosUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val getPhotosUseCase: GetPhotosUseCase
): ViewModel() {

    private val _errorEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val errorEvent: LiveData<Unit> = _errorEvent

    private val _repositories: MutableLiveData<List<Photo>> = MutableLiveData()
    val repositories: LiveData<List<Photo>> = _repositories

    private var page: Int = 0

    fun getPhotos() {
        viewModelScope.launch {
            getPhotosUseCase(page, PAGE_SIZE)
                .onSuccess {
                    _repositories.value = it
                }
                .onFailure { _errorEvent.value = Unit }
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            page += 1
            getPhotosUseCase(page, PAGE_SIZE)
                .onSuccess {
                    val newList = (repositories.value?.toMutableList() ?: emptyList()) + it
                    _repositories.value = newList
                }
                .onFailure { _errorEvent.value = Unit }
        }
    }
}
