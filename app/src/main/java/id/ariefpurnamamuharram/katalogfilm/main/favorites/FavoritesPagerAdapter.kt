package id.ariefpurnamamuharram.katalogfilm.main.favorites

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class FavoritesPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
    private val fmList = arrayListOf<Fragment>()
    private val titleList = arrayListOf<String>()

    fun addFragment(fragment: Fragment, title: String) {
        fmList.add(fragment)
        titleList.add(title)
    }

    override fun getItem(p0: Int) = fmList[p0]

    override fun getCount() = fmList.size

    override fun getPageTitle(position: Int) = titleList[position]
}