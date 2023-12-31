package com.nextstep.edu.domain.repository

import com.nextstep.edu.domain.model.Contents
import com.nextstep.edu.domain.model.Photo

interface RemoteRepository {

    suspend fun getPhotos(page: Int, limit: Int): Contents<Photo>
}
