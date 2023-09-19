package com.nextstep.edu.domain.usecase

import com.nextstep.edu.domain.model.Photo
import com.nextstep.edu.domain.repository.RemoteRepository
import javax.inject.Inject

class GetRepositoryUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(): Result<List<Photo>> {
        return runCatching { repository.getRepositories() }
    }
}
