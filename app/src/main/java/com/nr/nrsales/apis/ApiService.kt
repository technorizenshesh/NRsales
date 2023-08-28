package com.nr.nrsales.apis

import com.nr.nrsales.model.CsvResModel
import com.nr.nrsales.model.RegisterResModel
import com.nr.nrsales.utils.Constants
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.QueryMap
import retrofit2.http.Url

interface ApiService {

    @GET(Constants.LOGIN_URL)
    suspend fun loginUser(@Header("Authorization") auth: String, @QueryMap map: HashMap<String, Any>): Response<String>

    @POST(Constants.REGISTER_URL)
    suspend fun registerUser(@Header("Authorization") auth: String, @Body map: HashMap<String, Any>): Response<RegisterResModel>

    @GET()
    suspend fun getCsvList(@Url auth: String): Response<CsvResModel>

}
