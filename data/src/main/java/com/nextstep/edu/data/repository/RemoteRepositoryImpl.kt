package com.nextstep.edu.data.repository

import com.nextstep.edu.data.PhotoService
import com.nextstep.edu.data.model.toDomain
import com.nextstep.edu.domain.model.Contents
import com.nextstep.edu.domain.model.Photo
import com.nextstep.edu.domain.repository.RemoteRepository
import javax.inject.Inject

internal class RemoteRepositoryImpl @Inject constructor(
    private val photoService: PhotoService
): RemoteRepository {

    override suspend fun getPhotos(page: Int, limit: Int): Contents<Photo> {
        return photoService.getRepositories(page, limit).toDomain().copy(
            page = page + 1,
            pageSize = limit,
            hasNext = page <= 100
        )
    }
}
