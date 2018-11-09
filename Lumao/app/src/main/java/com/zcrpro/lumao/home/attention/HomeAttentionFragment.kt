package com.zcrpro.lumao.home.attention

import android.os.Bundle
import com.hazz.kotlinmvp.base.BaseFragment
import com.zcrpro.lumao.R

class HomeAttentionFragment:BaseFragment(){

    var mTitle: String? = null

    override fun getLayoutId(): Int = R.layout.home_attention_fragment

    override fun initView() {
    }

    override fun lazyLoad() {

    }

    companion object {
        fun getInstance(title: String): HomeAttentionFragment {
            val fragment = HomeAttentionFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

}
