package com.zcrpro.lumao

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v4.app.FragmentTransaction
import android.view.KeyEvent
import com.flyco.tablayout.listener.CustomTabEntity
import com.flyco.tablayout.listener.OnTabSelectListener
import com.hazz.kotlinmvp.base.BaseActivity
import com.zcrpro.lumao.discover.DiscoverFragment
import com.zcrpro.lumao.home.pager.HomePagerFragment
import com.zcrpro.lumao.mine.MineFragment
import com.zcrpro.lumao.tab.TabEntity
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*


/**
 * @author Jake.Ho
 * created: 2017/10/25
 * desc:
 */

class MainActivity : BaseActivity() {

    private val mTitles = arrayOf("每日精选","发现", "我的")

    // 未被选中的图标
    private val mIconUnSelectIds = intArrayOf(R.mipmap.ic_home_normal,R.mipmap.ic_discovery_normal, R.mipmap.ic_mine_normal)
    // 被选中的图标
    private val mIconSelectIds = intArrayOf(R.mipmap.ic_home_selected,R.mipmap.ic_discovery_selected,R.mipmap.ic_mine_selected)

    private val mTabEntities = ArrayList<CustomTabEntity>()

    private var mHomePagerFragment: HomePagerFragment? = null
    private var mDiscoverFragment: DiscoverFragment? = null
    private var mMineFragment: MineFragment? = null

    //默认为0
    private var mIndex = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        if (savedInstanceState != null) {
            mIndex = savedInstanceState.getInt("currTabIndex")
        }
        super.onCreate(savedInstanceState)
        initTab()
        tab_layout.currentTab = mIndex
        switchFragment(mIndex)

    }

    override fun layoutId(): Int {
        return R.layout.activity_main
    }


    //初始化底部菜单
    private fun initTab() {
        (0 until mTitles.size)
            .mapTo(mTabEntities) { TabEntity(mTitles[it], mIconSelectIds[it], mIconUnSelectIds[it]) }
        //为Tab赋值
        tab_layout.setTabData(mTabEntities)
        tab_layout.setOnTabSelectListener(object : OnTabSelectListener {
            override fun onTabSelect(position: Int) {
                //切换Fragment
                switchFragment(position)
            }
            override fun onTabReselect(position: Int) {

            }
        })
    }

    /**
     * 切换Fragment
     * @param position 下标
     */
    private fun switchFragment(position: Int) {
        val transaction = supportFragmentManager.beginTransaction()
        hideFragments(transaction)
        when (position) {
            0 -> mHomePagerFragment?.let {
                transaction.show(it)
            } ?: HomePagerFragment.getInstance(mTitles[position]).let {
                mHomePagerFragment = it
                transaction.add(R.id.fl_container, it, "homepager")
            }
            1 -> mDiscoverFragment?.let {
                transaction.show(it)
            } ?: DiscoverFragment.getInstance(mTitles[position]).let {
                mDiscoverFragment = it
                transaction.add(R.id.fl_container, it, "discover")
            }
            2 -> mMineFragment?.let {
                transaction.show(it)
            } ?: MineFragment.getInstance(mTitles[position]).let {
                mMineFragment = it
                transaction.add(R.id.fl_container, it, "mine")
            }
            else -> {

            }
        }

        mIndex = position
        tab_layout.currentTab = mIndex
        transaction.commitAllowingStateLoss()
    }


    /**
     * 隐藏所有的Fragment
     * @param transaction transaction
     */
    private fun hideFragments(transaction: FragmentTransaction) {
        mHomePagerFragment?.let { transaction.hide(it) }
        mDiscoverFragment?.let { transaction.hide(it) }
        mMineFragment?.let { transaction.hide(it) }
    }

    @SuppressLint("MissingSuperCall")
    override fun onSaveInstanceState(outState: Bundle) {
        if (tab_layout != null) {
            outState.putInt("currTabIndex", mIndex)
        }
    }

    override fun initView() {

    }

    override fun initData() {

    }

    override fun start() {

    }

    private var mExitTime: Long = 0

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (System.currentTimeMillis().minus(mExitTime) <= 2000) {
                finish()
            } else {
                mExitTime = System.currentTimeMillis()
                showToast("再按一次退出程序")
            }
            return true
        }
        return super.onKeyDown(keyCode, event)
    }
}
