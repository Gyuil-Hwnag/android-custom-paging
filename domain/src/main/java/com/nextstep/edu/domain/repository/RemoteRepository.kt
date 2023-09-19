package com.nextstep.edu.domain.repository

import com.nextstep.edu.domain.model.Photo

interface RemoteRepository {

    suspend fun getRepositories(): List<Photo>
}
