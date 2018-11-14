package com.zcrpro.lumao.discover

import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import com.zcrpro.lumao.R
import com.zcrpro.lumao.discover.adapter.DiscoverAdapter
import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.home.contract.DiscoverContract
import com.zcrpro.lumao.home.presenter.DiscoverPresenter
import com.zcrpro.lumao.showToast
import kotlinx.android.synthetic.main.discover_fragment.*


@Suppress("DEPRECATION")
class DiscoverFragment : BaseFragment(), DiscoverContract.View {

    private var page: Int = 1

    var mTitle: String? = null

    private var itemList = ArrayList<DiscoverBean.HotEvent>()

    private val mPresenter by lazy { DiscoverPresenter() }

    private val mDiscoverAdapter by lazy { activity?.let { DiscoverAdapter(it, itemList) } }

    /**
     * 是否加载更多
     */
    private var loadingMore = false

    override fun setData(discoverBean: DiscoverBean) {
        loadingMore = false
        itemList = ArrayList(discoverBean.hotEvents)
        mDiscoverAdapter?.addData(itemList)
    }

    override fun setMoreData(discoverBean: DiscoverBean) {
    }

    /**
     * 显示错误信息
     */
    override fun showError(errorMsg: String, errorCode: Int) {
        showToast(errorMsg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            multipleStatusView.showNoNetwork()
        } else {
            multipleStatusView.showError()
        }
    }

    override fun showLoading() {
        multipleStatusView.showLoading()

    }

    override fun dismissLoading() {
        multipleStatusView.showContent()
    }

    override fun initView() {

        mRecyclerView.layoutManager = LinearLayoutManager(activity)
        mRecyclerView.adapter = mDiscoverAdapter
        //实现自动加载
        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                val itemCount = mRecyclerView.layoutManager.itemCount
                val lastVisibleItem = (mRecyclerView.layoutManager as LinearLayoutManager).findLastVisibleItemPosition()
                if (!loadingMore && lastVisibleItem == (itemCount - 1)) {
//                    loadingMore = true
//                    page++
//                    mPresenter.loadMoreData(page)
                }
            }
        })

        mLayoutStatusView = multipleStatusView
    }

    override fun lazyLoad() {
        page = 1
        mPresenter.loadData(page)
    }

    override fun getLayoutId(): Int = R.layout.discover_fragment

    companion object {
        fun getInstance(title: String): DiscoverFragment {
            val fragment = DiscoverFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    private fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }

    init {
        mPresenter.attachView(this)
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }

}