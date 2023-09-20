package com.nextstep.edu.data

import com.nextstep.edu.data.model.PhotoResponse
import retrofit2.http.GET
import retrofit2.http.Query

internal interface PhotoService {

    /**
     * gitRepository 리스트 가져오기
     **/
    @GET("v2/list")
    suspend fun getRepositories(
        @Query("page") page: Int,
        @Query("limit") limit: Int
    ): List<PhotoResponse>
}
