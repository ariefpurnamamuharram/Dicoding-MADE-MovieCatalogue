package id.ariefpurnamamuharram.katalogfilm.main.favorites

import android.os.Bundle
import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.main.favorites.movies.FavoriteMoviesFragment
import id.ariefpurnamamuharram.katalogfilm.main.favorites.tvshows.FavoriteTVShowsFragment
import org.jetbrains.anko.find

class FavoritesMainFragment : Fragment() {
    private lateinit var viewPager: ViewPager
    private lateinit var viewTabs: TabLayout
    private lateinit var viewPagerAdapter: FavoritesPagerAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_favorites, container, false)

        viewPager = rootView.find(R.id.viewpager_favorites)
        viewTabs = rootView.find(R.id.tab_favorites)
        viewPagerAdapter = FavoritesPagerAdapter(childFragmentManager)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewPagerAdapter.addFragment(FavoriteMoviesFragment(), resources.getString(R.string.movies))
        viewPagerAdapter.addFragment(FavoriteTVShowsFragment(), resources.getString(R.string.tv_shows))

        viewPager.adapter = viewPagerAdapter
        viewTabs.setupWithViewPager(viewPager)
    }

    companion object {
        fun newInstance(): FavoritesMainFragment = FavoritesMainFragment()
    }
}