package com.zcrpro.lumao.home

import android.os.Bundle
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hazz.kotlinmvp.base.BaseFragment
import com.hazz.kotlinmvp.net.exception.ErrorStatus
import com.hazz.kotlinmvp.utils.StatusBarUtil
import com.scwang.smartrefresh.header.MaterialHeader
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.adapter.HomeAdapter
import com.zcrpro.lumao.home.bean.HomeBean
import com.zcrpro.lumao.home.contract.HomeContract
import com.zcrpro.lumao.home.presenter.HomePresenter
import com.zcrpro.lumao.showToast
import kotlinx.android.synthetic.main.home_fragment.*

@Suppress("DEPRECATION")
/**
 * Created by xuhao on 2017/11/8.
 * 首页精选
 */

class HomeFragment : BaseFragment(), HomeContract.View {

    private var page: Int = 1
    var mTitle: String? = null

    private var isRefresh = false
    private var mMaterialHeader: MaterialHeader? = null

    private var mHomeAdapter: HomeAdapter? = null

    private var CanloadingMore = true

    private val mPresenter by lazy { HomePresenter() }

    private var post_id: Int = 0 //第一次请求记录下最后一个post_id的值 用于翻页

    override fun setHomeData(homeBean: HomeBean) {
        post_id = homeBean.feedList[homeBean.feedList.size-1].post_id
        mLayoutStatusView?.showContent()
        // Adapter
        mHomeAdapter = activity?.let { HomeAdapter(it, homeBean.feedList) }
        mRecyclerView.adapter = mHomeAdapter
        mRecyclerView.layoutManager = linearLayoutManager
        mRecyclerView.itemAnimator = DefaultItemAnimator()
    }

    override fun setMoreData(homeBean: HomeBean) {
        post_id = homeBean.feedList[homeBean.feedList.size-1].post_id
        mHomeAdapter?.addItemData(homeBean.feedList)
    }

    override fun showError(msg: String, errorCode: Int) {
        showToast(msg)
        if (errorCode == ErrorStatus.NETWORK_ERROR) {
            mLayoutStatusView?.showNoNetwork()
        } else {
            mLayoutStatusView?.showError()
        }
    }

    override fun showLoading() {
        if (!isRefresh) {
            isRefresh = false
            mLayoutStatusView?.showLoading()
        }
    }

    override fun dismissLoading() {
        mRefreshLayout.finishRefresh()
    }

    override fun initView() {
        mPresenter.attachView(this)

        //内容跟随偏移
        mRefreshLayout.setEnableHeaderTranslationContent(true)
        mRefreshLayout.setOnRefreshListener {
            isRefresh = true
            page = 1
            mPresenter.loadData(page)
        }
        mMaterialHeader = mRefreshLayout.refreshHeader as MaterialHeader?
        //打开下拉刷新区域块背景:
        mMaterialHeader?.setShowBezierWave(false)
        //设置下拉刷新主题颜色
        mRefreshLayout.setPrimaryColorsId(R.color.color_title_bg, R.color.color_title_bg)

        mRecyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrollStateChanged(recyclerView: RecyclerView?, newState: Int) {
                super.onScrollStateChanged(recyclerView, newState)
                if (newState == RecyclerView.SCROLL_STATE_IDLE) {
                    val childCount = mRecyclerView.childCount
                    val itemCount = mRecyclerView.layoutManager.itemCount
                    val firstVisibleItem =
                        (mRecyclerView.layoutManager as LinearLayoutManager).findFirstVisibleItemPosition()
                    if (firstVisibleItem + childCount == itemCount) {
                        page++
                        mPresenter.loadMoreData(page, post_id,"loadmore") //图虫规定加载更多传loadmore
                    }
                }
            }

            //RecyclerView滚动的时候调用
            override fun onScrolled(recyclerView: RecyclerView?, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val currentVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition()
                if (currentVisibleItemPosition == 0) {
                    //背景设置为透明
                    toolbar.setBackgroundColor(getColor(R.color.color_translucent))
                    iv_search.setImageResource(R.mipmap.ic_action_search_black)
                    tv_header_title.text = ""
                } else {
                    if (mHomeAdapter?.mData!!.size > 1) {
                        toolbar.setBackgroundColor(getColor(R.color.color_title_bg))
                        iv_search.setImageResource(R.mipmap.ic_action_search_black)
                        val itemList = mHomeAdapter!!.mData
                        val item = itemList[currentVisibleItemPosition]
                        tv_header_title.text = item.title
                    }
                }
            }
        })

        iv_search.setOnClickListener { openSearchActivity() }

        mLayoutStatusView = multipleStatusView

        //状态栏透明和间距处理
        activity?.let { StatusBarUtil.darkMode(it) }
        activity?.let { StatusBarUtil.setPaddingSmart(it, toolbar) }

    }

    override fun lazyLoad() {
        mPresenter.loadData(page)
    }

    companion object {
        fun getInstance(title: String): HomeFragment {
            val fragment = HomeFragment()
            val bundle = Bundle()
            fragment.arguments = bundle
            fragment.mTitle = title
            return fragment
        }
    }

    private val linearLayoutManager by lazy {
        LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }

    override fun getLayoutId(): Int = R.layout.home_fragment

    fun getColor(colorId: Int): Int {
        return resources.getColor(colorId)
    }

    private fun openSearchActivity() {
    }

    override fun onDestroy() {
        super.onDestroy()
        mPresenter.detachView()
    }
}
