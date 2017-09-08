package uk.co.garyhomewood.marvelcomics.comiclist

import android.support.v4.app.Fragment
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import uk.co.garyhomewood.marvelcomics.R
import uk.co.garyhomewood.marvelcomics.di.Injector
import uk.co.garyhomewood.marvelcomics.model.ComicDataWrapper

/**
 * A placeholder fragment containing a simple view.
 */
class ComicListFragment : Fragment(), ComicListInterface.View {
    override fun showProgressIndicator(show: Boolean) {
    }

    override fun showList(data: ComicDataWrapper) {
    }

    lateinit var userActionsListener: ComicListInterface.UserActions

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        userActionsListener = ComicListPresenter(this,
                Injector.provideComicsService(),
                resources.getString(R.string.marvel_api_public_key),
                resources.getString(R.string.marvel_api_private_key))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_comic_list, container, false)
    }

    override fun onViewCreated(view: View?, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        userActionsListener.loadList()
    }
}
