package com.zcrpro.lumao.home.pager

import android.graphics.Typeface
import android.os.Bundle
import com.hazz.kotlinmvp.base.BaseFragment
import com.zcrpro.lumao.MyApplication
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.HomeFragment
import com.zcrpro.lumao.home.adapter.HomePagerAdapter
import com.zcrpro.lumao.home.attention.HomeAttentionFragment
import kotlinx.android.synthetic.main.home_pager_fragment.*

class HomePagerFragment : BaseFragment() {

    var mTitle: String? = null

    var pagerAdapter:HomePagerAdapter?=null

    private var textTypeface: Typeface?=null

    override fun initView() {

        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")

        tv_attention.typeface = textTypeface
        tv_recommend.typeface = textTypeface

        pagerAdapter = HomePagerAdapter(activity!!.supportFragmentManager)
        pagerAdapter!!.addFragments(HomeAttentionFragment(),"HomeAttentionFragment")
        pagerAdapter!!.addFragments(HomeFragment(),"HomeFragment")
        //adding pageradapter to viewpager
        viewpager.adapter = pagerAdapter
    }

    override fun lazyLoad() {

    }

    override fun getLayoutId(): Int = R.layout.home_pager_fragment

    companion object {
        fun getInstance(title: String): HomePagerFragment {
            val fragment = HomePagerFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}
