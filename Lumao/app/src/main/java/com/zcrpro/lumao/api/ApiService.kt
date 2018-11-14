package com.hazz.kotlinmvp.api

import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.home.bean.HomeBean
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * Created by xuhao on 2017/11/16.
 * Api 接口
 */

interface ApiService{
    /**
     * 首页精选第一页数据
     */
    @GET("feed-app?")
    fun getFirstHomeData(@Query("page") page:Int): Observable<HomeBean>

    /**
     * 首页精选加载更多
     */
    @GET("feed-app?")
    fun loadHomeDataMore(@Query("page") page:Int,@Query("post_id") post_id:Int,@Query("type") type:String): Observable<HomeBean>

    /**
     * 发现第一页数据
     */
    @GET("discover-app?")
    fun getFirstDiscoverData(@Query("page") page:Int): Observable<DiscoverBean>

    /**
     * 发现加载更多
     */
    @GET("discover-app?")
    fun loadDiscoverDataMore(@Query("page") page:Int,@Query("post_id") post_id:Int,@Query("type") type:String): Observable<DiscoverBean>
}