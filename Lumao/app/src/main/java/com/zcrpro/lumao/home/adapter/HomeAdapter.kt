package com.zcrpro.lumao.home.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions
import com.hazz.kotlinmvp.glide.GlideApp
import com.hazz.kotlinmvp.view.recyclerview.ViewHolder
import com.zcrpro.lumao.R
import com.zcrpro.lumao.home.bean.HomeBean
import com.zcrpro.lumao.view.recyclerview.adapter.CommonAdapter

class HomeAdapter(context: Context, data: ArrayList<HomeBean.Feed>) : CommonAdapter<HomeBean.Feed>(context, data, -1) {

    override fun bindData(holder: ViewHolder, data: HomeBean.Feed, position: Int) {
        setItem(holder, mData[position])
    }

    /**
     * 加载布局
     */
    private fun inflaterView(mLayoutId: Int, parent: ViewGroup): View {
        //创建view
        val view = mInflater?.inflate(mLayoutId, parent, false)
        return view ?: View(parent.context)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(inflaterView(R.layout.home_content_item, parent))
    }

    /**
     * 添加更多数据
     */
    fun addItemData(itemList: ArrayList<HomeBean.Feed>) {
        this.mData.addAll(itemList)
        notifyDataSetChanged()
    }

    /**
     * 加载 content item
     */
    private fun setItem(holder: ViewHolder, itemData: HomeBean.Feed) {

        var avatar_icon = itemData.site.icon
        var avatar_name = itemData.site.name

        // 如果提供者信息为空，就显示默认
        if (itemData.images.isEmpty()) {
            holder.getView<ImageView>(R.id.iv_home_recommend).setImageResource(R.color.placeholder_color)
        } else {
            GlideApp.with(mContext)
                .load("https://photo.tuchong.com/" + itemData.images[0].user_id + "/f/" + itemData.images[0].img_id + ".jpg")
                .placeholder(R.color.placeholder_color).centerCrop()
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_home_recommend))
        }

        // 如果提供者信息为空，就显示默认
        if (avatar_icon.isEmpty()) {
            GlideApp.with(mContext)
            holder.getView<ImageView>(R.id.iv_avatar).setImageResource(R.color.placeholder_color)
        } else {
            GlideApp.with(mContext)
                .load(avatar_icon)
                .placeholder(R.color.placeholder_color).circleCrop()
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_avatar))
        }

        if (!avatar_name.isEmpty()) {
            holder.setText(R.id.tv_title, avatar_name)
        }

        if(itemData.image_count ==0&&!itemData.title_image.url.isEmpty()){
            GlideApp.with(mContext)
                .load(itemData.title_image.url)
                .placeholder(R.color.placeholder_color).centerCrop()
                .transition(DrawableTransitionOptions().crossFade())
                .into(holder.getView(R.id.iv_home_recommend))
        }

        holder.setText(R.id.tv_favorites,itemData.favorites.toString())
        holder.setText(R.id.tv_comments,itemData.comments.toString())

//         holder.setOnItemClickListener(listener = View.OnClickListener {
//             goToVideoPlayer(mContext as Activity, holder.getView(R.id.iv_cover_feed), item)
//         })
    }
}