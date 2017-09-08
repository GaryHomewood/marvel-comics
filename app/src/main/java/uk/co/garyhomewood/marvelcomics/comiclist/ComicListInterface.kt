package uk.co.garyhomewood.marvelcomics.comiclist

import uk.co.garyhomewood.marvelcomics.model.ComicDataWrapper

/**
 * Created by homewoodg on 31/08/2017.
 */
interface ComicListInterface {
    interface View {
        fun showProgressIndicator(show: Boolean)
        fun showList(data: ComicDataWrapper)
    }

    interface UserActions {
        fun loadList()
    }
}