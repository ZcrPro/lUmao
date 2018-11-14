package com.zcrpro.lumao.home.bean


data class DiscoverBean(
    val banners: List<Banner>,
    val categories: List<Category>,
    val hotEvents: List<HotEvent>,
    val result: String
) {

    data class Banner(
        val src: String,
        val url: String
    )

    data class Category(
        val tag_id: Int,
        val tag_name: String
    )

    data class HotEvent(
        val app_url: String,
        val category: List<Any>,
        val competition_type: Int,
        val created_at: String,
        val deadline: String,
        val dueDays: Int,
        val end_at: String,
        val event_type: String,
        val image_count: Int,
        val image_posts: List<Any>,
        val images: List<String>,
        val introduction_id: Int,
        val introduction_url: String,
        val list_banner: Boolean,
        val new_posts: String,
        val participants: String,
        val posts: String,
        val prize_desc: String,
        val prize_url: String,
        val remainingDays: Int,
        val status: String,
        val tag_id: String,
        val tag_name: String,
        val title: String,
        val url: String
    )
}