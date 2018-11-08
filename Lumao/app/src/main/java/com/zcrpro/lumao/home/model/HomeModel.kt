package com.zcrpro.lumao.home.model

import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.zcrpro.lumao.home.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class HomeModel{

    /**
     * 获取首页 Banner 数据
     */
    fun requestHomeData(page:Int): Observable<HomeBean> {
        return RetrofitManager.service.getFirstHomeData(page)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取首页 Banner 数据
     */
    fun loadMoreData(page:Int,post_id:Int,type:String): Observable<HomeBean> {
        return RetrofitManager.service.loadHomeDataMore(page,post_id,type)
            .compose(SchedulerUtils.ioToMain())
    }
}
