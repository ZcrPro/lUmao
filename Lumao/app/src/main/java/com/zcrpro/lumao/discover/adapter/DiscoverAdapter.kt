package com.zcrpro.lumao.discover.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.hazz.kotlinmvp.view.recyclerview.MultipleType
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.view.recyclerview.adapter.CommonAdapter

class DiscoverAdapter(context: Context, dataList: ArrayList<DiscoverBean.HotEvent>)
    : CommonAdapter<DiscoverBean.HotEvent>(context, dataList, object : MultipleType<DiscoverBean.HotEvent> {
    override fun getLayoutId(item:DiscoverBean.HotEvent, position: Int): Int {
     return  R.layout.item_discover
    }
}) {
    fun addData(dataList: ArrayList<DiscoverBean.HotEvent>) {
        this.mData.addAll(dataList)
        notifyDataSetChanged()
    }

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: DiscoverBean.HotEvent, position: Int) {
        sethotEventInfo(data, holder)
    }

    private fun sethotEventInfo(item: DiscoverBean.HotEvent, holder: ViewHolder) {
        val hotEvent = item
//        /**
//         * 加载作者头像
//         */
//        holder.setImagePath(R.id.iv_avatar, object : ViewHolder.HolderImageLoader(headerData?.icon!!) {
//            override fun loadImage(iv: ImageView, path: String) {
//                GlideApp.with(mContext)
//                    .load(path)
//                    .placeholder(R.mipmap.default_avatar).circleCrop()
//                    .transition(DrawableTransitionOptions().crossFade())
//                    .into(holder.getView(R.id.iv_avatar))
//            }
//
//        })
        holder.setText(R.id.tv_title, hotEvent?.title)
        holder.setText(R.id.tv_desc, hotEvent?.tag_name)

        val recyclerView = holder.getView<RecyclerView>(R.id.fl_recyclerView)
        /**
         * 设置嵌套水平的 RecyclerView
         */
        recyclerView.layoutManager = LinearLayoutManager(mContext as Activity, LinearLayoutManager.HORIZONTAL, false)
        recyclerView.adapter =
                DIscoverHorizontalAdapter(mContext,mData, R.layout.item_discover_horizontal)

    }
}