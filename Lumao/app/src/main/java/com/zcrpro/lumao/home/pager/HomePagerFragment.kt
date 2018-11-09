package com.zcrpro.lumao.home.pager

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Activity
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.support.design.widget.TabItem
import android.support.design.widget.TabLayout
import android.support.v4.view.ViewPager
import android.view.View
import android.widget.TextView
import com.hazz.kotlinmvp.base.BaseFragment
import com.zcrpro.lumao.MyApplication
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.HomeFragment
import com.zcrpro.lumao.home.adapter.HomePagerAdapter
import com.zcrpro.lumao.home.attention.HomeAttentionFragment
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.home_pager_fragment.*
import kotlinx.android.synthetic.main.home_pager_fragment.view.*
import java.lang.ref.WeakReference
import kotlin.jvm.internal.Ref

class HomePagerFragment : BaseFragment() {

    var mTitle: String? = null

    var pagerAdapter: HomePagerAdapter? = null

    private var textTypeface: Typeface? = null

    override fun initView() {

        textTypeface = Typeface.createFromAsset(MyApplication.context.assets, "fonts/Lobster-1.4.otf")

        tv_attention.typeface = textTypeface
        tv_recommend.typeface = textTypeface

        pagerAdapter = HomePagerAdapter(activity!!.supportFragmentManager)
        pagerAdapter!!.addFragments(HomeAttentionFragment(), "关注")
        pagerAdapter!!.addFragments(HomeFragment(), "推荐")
        //adding pageradapter to viewpager
        viewpager.adapter = pagerAdapter
        indicator.setTabLayoutAndViewPager(tabs, viewpager)
        tabs.setTabTextColors(getColor(R.color.color_gray),getColor(R.color.color_black))
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

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }
}
