package id.ariefpurnamamuharram.katalogfilm.db

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteMovie
import id.ariefpurnamamuharram.katalogfilm.db.favorites.FavoriteTVShow
import org.jetbrains.anko.db.*

class DbOpenHelper(ctx: Context) : ManagedSQLiteOpenHelper(ctx, "KatalogFilm.db", null, 1) {

    companion object {
        private var instance: DbOpenHelper? = null

        @Synchronized
        fun getInstance(ctx: Context): DbOpenHelper {
            if (instance == null) {
                instance = DbOpenHelper(ctx.applicationContext)
            }
            return instance as DbOpenHelper
        }
    }

    override fun onCreate(db: SQLiteDatabase?) {
        db?.createTable(
            FavoriteMovie.TABLE_FAVORITE_MOVIE, true,
            FavoriteMovie.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            FavoriteMovie.MOVIE_ID to TEXT,
            FavoriteMovie.TITLE to TEXT,
            FavoriteMovie.RELEASE_DATE to TEXT,
            FavoriteMovie.VOTE_AVERAGE to TEXT,
            FavoriteMovie.OVERVIEW to TEXT,
            FavoriteMovie.POSTER_URL to TEXT
        )

        db?.createTable(
            FavoriteTVShow.TABLE_FAVORITE_TV_SHOW, true,
            FavoriteTVShow.ID to INTEGER + PRIMARY_KEY + AUTOINCREMENT + UNIQUE,
            FavoriteTVShow.TV_SHOW_ID to TEXT,
            FavoriteTVShow.ORIGINAL_NAME to TEXT,
            FavoriteTVShow.FIRST_AIR_DATE to TEXT,
            FavoriteTVShow.VOTE_AVERAGE to TEXT,
            FavoriteTVShow.OVERVIEW to TEXT,
            FavoriteTVShow.POSTER_URL to TEXT
        )
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        db?.dropTable(FavoriteMovie.TABLE_FAVORITE_MOVIE, true)
        db?.dropTable(FavoriteTVShow.TABLE_FAVORITE_TV_SHOW, true)
    }
}

val Context.db: DbOpenHelper
    get() = DbOpenHelper.getInstance(applicationContext)