package com.zcrpro.lumao.mine

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import com.hazz.kotlinmvp.base.BaseFragment
import com.zcrpro.lumao.R

@Suppress("DEPRECATION")
/**
 * Created by xuhao on 2017/11/8.
 * 首页精选
 */

class MineFragment : BaseFragment() {

    private var mTitle: String? = null

    override fun initView() {
    }

    override fun lazyLoad() {
    }

    override fun getLayoutId(): Int = R.layout.mine_fragment

    companion object {
        fun getInstance(title: String): MineFragment {
            val fragment = MineFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}
