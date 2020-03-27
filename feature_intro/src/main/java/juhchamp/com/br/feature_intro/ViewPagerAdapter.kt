package juhchamp.com.br.feature_intro

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

class ViewPagerAdapter(fm: FragmentManager):
    FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {

    private val mFragmentList = ArrayList<Fragment>()
    //private val mFragmentTitleList = ArrayList<String>()

    override fun getItem(position: Int): Fragment {
        return mFragmentList[position]
    }

    override fun getCount(): Int {
        return mFragmentList.size
    }

    /*override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitleList.get(position)
    }*/

    fun addFragment(fragment: Fragment) {
        mFragmentList.add(fragment)
        //mFragmentTitleList.add(title)
    }
}