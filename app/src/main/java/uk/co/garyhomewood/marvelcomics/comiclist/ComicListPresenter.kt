package uk.co.garyhomewood.marvelcomics.comiclist

import android.content.Context
import android.content.res.Resources
import android.util.Log
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import uk.co.garyhomewood.marvelcomics.R
import uk.co.garyhomewood.marvelcomics.api.ComicsService
import uk.co.garyhomewood.marvelcomics.model.ComicDataWrapper
import java.security.MessageDigest
import uk.co.garyhomewood.marvelcomics.extensions.MD5

/**
 * Created by homewoodg on 31/08/2017.
 */
class ComicListPresenter(
        private val view : ComicListInterface.View,
        private val service : ComicsService,
        private val publicKey : String,
        private val privateKey : String
) : ComicListInterface.UserActions {

    override fun loadList() {

        val timestamp : String = System.currentTimeMillis().toString()
        val hash = ("$timestamp$privateKey$publicKey").MD5()

        val call: Observable<ComicDataWrapper> = service.getComics(
                ts = timestamp,
                apikey = publicKey,
                hash = hash,
                limit = "100"
                )

        call.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .doOnSubscribe { view.showProgressIndicator(true) }
                .doFinally { view.showProgressIndicator(false) }
                .subscribe({
                    data: ComicDataWrapper ->
                        view.showList(data)
                }, {
                    t: Throwable? ->
                        Log.e("ComicListPresenter", t.toString())
                })
    }
}