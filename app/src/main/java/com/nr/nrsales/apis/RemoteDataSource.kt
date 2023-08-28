package com.nr.nrsales.apis

import javax.inject.Inject

class RemoteDataSource @Inject constructor(private val apiService: ApiService) {
    suspend fun loginUser(params: HashMap<String, Any>) =
        apiService.loginUser("867bb48f-ade8-4688-954b-12668ea07977", params)
    suspend fun registerUser(params: HashMap<String, Any>) =
        apiService.registerUser("867bb48f-ade8-4688-954b-12668ea07977", params)

}