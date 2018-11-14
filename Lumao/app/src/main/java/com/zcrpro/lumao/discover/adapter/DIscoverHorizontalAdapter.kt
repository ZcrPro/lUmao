package com.zcrpro.lumao.discover.adapter

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.glide.GlideApp
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.bean.DiscoverBean
import com.zcrpro.lumao.view.recyclerview.adapter.CommonAdapter

/**
 * Created by xuhao on 2017/12/7.
 * desc: 关注   水平的 RecyclerViewAdapter
 */
class DIscoverHorizontalAdapter(mContext: Context, categoryList: ArrayList<DiscoverBean.HotEvent>, layoutId: Int) :
        CommonAdapter<DiscoverBean.HotEvent>(mContext, categoryList, layoutId) {

    /**
     * 绑定数据
     */
    override fun bindData(holder: ViewHolder, data: DiscoverBean.HotEvent, position: Int) {
        holder.setImagePath(R.id.iv_cover_feed, object : ViewHolder.HolderImageLoader(data.images[0]) {
            override fun loadImage(iv: ImageView, path: String) {
                // 加载封页图
                GlideApp.with(mContext)
                        .load(path)
                        .placeholder(R.color.placeholder_color)
                        .transition(DrawableTransitionOptions().crossFade())
                        .into(holder.getView(R.id.iv_cover_feed))
            }

        })

        //横向 RecyclerView 封页图下面标题
        holder.setText(R.id.tv_title, data?.title ?: "")

//        // 格式化时间
//        val timeFormat = durationFormat(horizontalItemData?.created_at)
        //标签
//        with(holder) {
//            Logger.d("horizontalItemData===title:${horizontalItemData?.title}tag:${horizontalItemData?.tags?.size}")
//
//            if (horizontalItemData?.tags != null && horizontalItemData.tags.size > 0) {
//                setText(R.id.tv_tag, "#${horizontalItemData.tags[0].name} / $timeFormat")
//            }else{
//                setText(R.id.tv_tag,"#$timeFormat")
//            }
//
//            setOnItemClickListener(listener = View.OnClickListener {
//            })
//        }
    }
}
