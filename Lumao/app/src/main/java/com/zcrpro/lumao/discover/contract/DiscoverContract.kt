package com.zcrpro.lumao.home.contract

import com.hazz.kotlinmvp.base.IBaseView
import com.hazz.kotlinmvp.base.IPresenter
import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.home.bean.HomeBean

/**
 * Created by xuhao on 2017/11/8.
 * 契约类
 */

interface DiscoverContract {

    interface View : IBaseView {

        /**
         * 设置第一次请求的数据
         */
        fun setData(discoverBean: DiscoverBean)

        /**
         * 设置加载更多的数据
         */
        fun setMoreData(discoverBean: DiscoverBean)

        /**
         * 显示错误信息
         */
        fun showError(msg: String,errorCode:Int)

    }

    interface Presenter : IPresenter<View> {

        /**
         * 加载数据
         */
        fun loadData(page:Int)

        fun loadMoreData(page: Int,post_id:Int,type:String)
    }

}