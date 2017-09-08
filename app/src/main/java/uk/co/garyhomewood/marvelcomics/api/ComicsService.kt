package uk.co.garyhomewood.marvelcomics.api

import retrofit2.http.GET
import retrofit2.http.Query
import uk.co.garyhomewood.marvelcomics.model.ComicDataWrapper
import io.reactivex.Observable

/**
 * Created by homewoodg on 31/08/2017.
 */
interface ComicsService {
    @GET("comics")
    fun getComics(
            @Query("ts") ts: String,
            @Query("apikey") apikey: String,
            @Query("hash") hash: String,
            @Query("limit") limit : String
    ): Observable<ComicDataWrapper>
}