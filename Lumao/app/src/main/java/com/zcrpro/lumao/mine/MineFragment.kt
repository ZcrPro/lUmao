package com.zcrpro.lumao.mine

import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import com.hazz.kotlinmvp.base.BaseFragment
import com.zcrpro.lumao.R
import com.zcrpro.lumao.login.LoginActivity
import kotlinx.android.synthetic.main.mine_fragment.*

@Suppress("DEPRECATION")
/**
 * Created by xuhao on 2017/11/8.
 * 首页精选
 */

class MineFragment : BaseFragment() {

    private var mTitle: String? = null

    override fun initView() {
        tv_login.setOnClickListener { startActivity(Intent(activity,LoginActivity::class.java)) }
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
