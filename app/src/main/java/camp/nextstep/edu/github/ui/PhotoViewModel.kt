package camp.nextstep.edu.github.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.github.util.INIT_PAGE
import camp.nextstep.edu.github.util.PAGE_SIZE
import camp.nextstep.edu.github.util.SingleLiveEvent
import com.nextstep.edu.domain.model.Contents
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

    private val _photos: MutableLiveData<Contents<Photo>> = MutableLiveData()
    val photos: LiveData<Contents<Photo>> = _photos


    fun getPhotos() {
        viewModelScope.launch {
            getPhotosUseCase(INIT_PAGE, PAGE_SIZE)
                .onSuccess {
                    _photos.value = it
                }
                .onFailure { _errorEvent.value = Unit }
        }
    }

    fun nextPage() {
        viewModelScope.launch {
            if (photos.value?.hasNext == false) return@launch

            getPhotosUseCase((photos.value?.page ?: 0) + 1, PAGE_SIZE)
                .onSuccess {
                    it.content = (photos.value?.content?.toMutableList() ?: emptyList()) + it.content
                    _photos.value = it
                }
                .onFailure { _errorEvent.value = Unit }
        }
    }
}
