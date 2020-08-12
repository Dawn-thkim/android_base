package kr.taehoon.baseapplication.util

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.http.*

interface ApiService {

    @GET("/sample/test")
    fun getAppProfile(@Query("test") test:String): Single<JsonObject>
}