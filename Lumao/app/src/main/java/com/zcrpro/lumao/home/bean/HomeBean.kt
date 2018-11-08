package com.zcrpro.lumao.home.bean

data class HomeBean(
    val counts: Int,
    val feedList: ArrayList<Feed>,
    val is_history: Boolean,
    val message: String,
    val more: Boolean,
    val result: String
) {

    data class Feed(
        val author_id: String,
        val collected: Boolean,
        val comment_list_prefix: List<Any>,
        val comments: Int,
        val content: String,
        val created_at: String,
        val data_type: String,
        val delete: Boolean,
        val event_tags: List<Any>,
        val excerpt: String,
        val favorite_list_prefix: List<Any>,
        val favorites: Int,
        val image_count: Int,
        val images: List<Image>,
        val is_favorite: Boolean,
        val last_read: Boolean,
        val parent_comments: String,
        val passed_time: String,
        val post_id: Int,
        val published_at: String,
        val recom_type: String,
        val reward_list_prefix: List<Any>,
        val rewardable: Boolean,
        val rewards: String,
        val rqt_id: String,
        val site: Site,
        val site_id: String,
        val sites: List<Any>,
        val tags: List<String>,
        val title: String,
        val title_image: TitleImage,
        val type: String,
        val update: Boolean,
        val url: String,
        val views: Int
    )

    data class Image(
        val description: String,
        val excerpt: String,
        val height: Int,
        val img_id: Int,
        val title: String,
        val user_id: Int,
        val width: Int
    )

    data class TitleImage(
        val width: String,
        val height: String,
        val url: String
    )

    data class Site(
        val description: String,
        val domain: String,
        val followers: Int,
        val icon: String,
        val is_following: Boolean,
        val name: String,
        val site_id: String,
        val type: String,
        val url: String,
        val verification_list: List<Any>,
        val verifications: Int,
        val verified: Boolean,
        val verified_reason: String,
        val verified_type: Int
    )
}