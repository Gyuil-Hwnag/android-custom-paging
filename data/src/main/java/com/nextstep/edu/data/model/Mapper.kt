package com.nextstep.edu.data.model

import com.nextstep.edu.domain.model.Photo

internal fun PhotoResponse.toDomain(): Photo {
    return Photo(
        id = this.id,
        author = this.author ?: "",
        url = url ?: ""
    )
}

internal fun List<PhotoResponse>.toDomain(): List<Photo> {
    return this.map { data ->
        data.toDomain()
    }
}
