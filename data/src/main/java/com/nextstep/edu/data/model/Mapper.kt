package com.nextstep.edu.data.model

import com.nextstep.edu.domain.model.Contents
import com.nextstep.edu.domain.model.Photo

internal fun PhotoResponse.toDomain(): Photo {
    return Photo(
        id = this.id,
        author = this.author ?: "",
        url = this.url ?: "",
        downloadUrl = this.downloadUrl
    )
}

internal fun List<PhotoResponse>.toDomain(): Contents<Photo> {
    return Contents(
        page = 0,
        pageSize = 0,
        hasNext = false,
        content = this.map { it.toDomain() }
    )
}
