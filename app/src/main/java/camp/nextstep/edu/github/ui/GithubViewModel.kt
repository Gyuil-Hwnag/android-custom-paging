package camp.nextstep.edu.github.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import camp.nextstep.edu.github.util.PAGING_SIZE
import camp.nextstep.edu.github.util.SingleLiveEvent
import com.nextstep.edu.domain.model.Photo
import com.nextstep.edu.domain.usecase.GetRepositoryUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class GithubViewModel @Inject constructor(
    private val getRepositoryUseCase: GetRepositoryUseCase
): ViewModel() {

    private val _errorEvent: SingleLiveEvent<Unit> = SingleLiveEvent()
    val errorEvent: LiveData<Unit> = _errorEvent

    private val _repositories: MutableLiveData<List<Photo>> = MutableLiveData()
    val repositories: LiveData<List<Photo>> = _repositories

    fun getRepositories() {
        viewModelScope.launch {
            getRepositoryUseCase(0, PAGING_SIZE)
                .onSuccess { _repositories.value = it }
                .onFailure { _errorEvent.value = Unit }
        }

    }

}
