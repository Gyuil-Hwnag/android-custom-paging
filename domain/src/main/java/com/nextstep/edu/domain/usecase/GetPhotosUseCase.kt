package com.nextstep.edu.domain.usecase

import com.nextstep.edu.domain.model.Contents
import com.nextstep.edu.domain.model.Photo
import com.nextstep.edu.domain.repository.RemoteRepository
import javax.inject.Inject

class GetPhotosUseCase @Inject constructor(
    private val repository: RemoteRepository
) {
    suspend operator fun invoke(page: Int, limit: Int): Result<Contents<Photo>> {
        return runCatching { repository.getPhotos(page, limit) }
    }
}
