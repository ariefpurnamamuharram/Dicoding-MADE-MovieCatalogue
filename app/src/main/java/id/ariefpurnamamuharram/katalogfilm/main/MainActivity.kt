package id.ariefpurnamamuharram.katalogfilm.main

import android.content.Intent
import android.os.Bundle
import android.provider.Settings
import android.support.design.widget.BottomNavigationView
import android.support.v4.app.Fragment
import android.support.v7.app.AppCompatActivity
import android.view.Menu
import android.view.MenuItem
import id.ariefpurnamamuharram.katalogfilm.R
import id.ariefpurnamamuharram.katalogfilm.main.films.FilmsFragment
import id.ariefpurnamamuharram.katalogfilm.main.movies.MoviesFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        bottomNav()
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)

        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        if (item?.itemId == R.id.action_change_settings) {
            val mIntent = Intent(Settings.ACTION_LOCALE_SETTINGS)
            startActivity(mIntent)
        }
        return super.onOptionsItemSelected(item)
    }

    private fun bottomNav() {
        bottom_nav.setOnNavigationItemSelectedListener(mItemSelectedListener)
        bottom_nav.selectedItemId = R.id.nav_movies
        bottomNavMenuEnable(navMovies = false, navFilms = true)
    }

    private val mItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.nav_movies -> {
                openFragment(MoviesFragment.newInstance())
                bottomNavMenuEnable(
                    navMovies = false, navFilms = true
                )
                return@OnNavigationItemSelectedListener true
            }

            R.id.nav_films -> {
                openFragment(FilmsFragment.newInstance())
                bottomNavMenuEnable(
                    navMovies = true, navFilms = false
                )
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun bottomNavMenuEnable(navMovies: Boolean, navFilms: Boolean) {
        bottom_nav.menu.getItem(0).isEnabled = navMovies
        bottom_nav.menu.getItem(1).isEnabled = navFilms
    }

    private fun openFragment(fragment: Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, fragment)
        transaction.commit()
    }
}
