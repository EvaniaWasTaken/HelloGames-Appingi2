package fr.epita.hellogames

import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.Call

interface WebServiceInterface {
    @GET("game/list")
    fun getGameList(): Call<List<GameObject>>

    @GET("game/details")
    fun getGameDetails(@Query("game_id") game_id : Int): Call<GameDetail>
}