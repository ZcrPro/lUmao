package com.zcrpro.lumao.home.model

import com.hazz.kotlinmvp.rx.scheduler.SchedulerUtils
import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.home.bean.HomeBean
import io.reactivex.Observable

/**
 * Created by xuhao on 2017/11/21.
 * desc: 首页精选 model
 */

class DiscoverModel{

    /**
     * 获取discover数据
     */
    fun requestDiscoverData(page:Int): Observable<DiscoverBean> {
        return RetrofitManager.service.getFirstDiscoverData(page)
            .compose(SchedulerUtils.ioToMain())
    }

    /**
     * 获取discover数据
     */
    fun loadMoreData(page:Int,post_id:Int,type:String): Observable<DiscoverBean> {
        return RetrofitManager.service.loadDiscoverDataMore(page,post_id,type)
            .compose(SchedulerUtils.ioToMain())
    }
}
