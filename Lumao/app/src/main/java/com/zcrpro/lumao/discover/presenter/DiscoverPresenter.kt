package com.zcrpro.lumao.home.presenter

import com.hazz.kotlinmvp.base.BasePresenter
import com.zcrpro.lumao.home.bean.HomeBean
import com.zcrpro.lumao.home.contract.DiscoverContract
import com.zcrpro.lumao.home.contract.HomeContract
import com.zcrpro.lumao.home.model.DiscoverModel
import com.zcrpro.lumao.home.model.HomeModel

/**
 * Created by xuhao on 2017/11/8.
 * 首页精选的 Presenter
 * (数据是 Banner 数据和一页数据组合而成的 HomeBean,查看接口然后在分析就明白了)
 */

class DiscoverPresenter : BasePresenter<DiscoverContract.View>(), DiscoverContract.Presenter {

    private val discoverModel: DiscoverModel by lazy {

        DiscoverModel()
    }

    override fun loadData(page:Int) {
        // 检测是否绑定 View
        checkViewAttached()
        mRootView?.showLoading()
        val disposable = discoverModel.requestDiscoverData(page)
            .subscribe({ discoverbean->
                mRootView?.apply {
                    dismissLoading()
                    // 重新赋值 Banner 长度
                    setData(discoverbean!!)
                }

            }, { t ->
                mRootView?.apply {
                    dismissLoading()
                    showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                }
            })
        addSubscription(disposable)
    }

    override fun loadMoreData(page:Int,post_id:Int,type:String) {
        val disposable = discoverModel.loadMoreData(page,post_id,type)
                .subscribe({ discoverbean->
                    mRootView?.apply {
                        setMoreData(discoverbean!!)
                    }
                },{ t ->
                    mRootView?.apply {
                        showError(ExceptionHandle.handleException(t),ExceptionHandle.errorCode)
                    }
                })

        if (disposable != null) {
            addSubscription(disposable)
        }
    }
}