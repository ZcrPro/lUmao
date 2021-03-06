package com.zcrpro.lumao.home.adapter

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentStatePagerAdapter

class HomePagerAdapter(fm:FragmentManager): FragmentStatePagerAdapter(fm) {

    var mFm = fm
    var mFragmentItems:ArrayList<Fragment> = ArrayList()
    var mFragmentTitles:ArrayList<String> = ArrayList()

    //we need to create function to add fragments
    fun addFragments(fragmentItem:Fragment,fragmentTitle:String){
        mFragmentItems.add(fragmentItem)
        mFragmentTitles.add(fragmentTitle)
    }

    override fun getItem(position: Int): Fragment {
        return mFragmentItems[position]
    }

    override fun getCount(): Int {
        return mFragmentItems.size
    }

    override fun getPageTitle(position: Int): CharSequence {
        return mFragmentTitles[position]
    }

}