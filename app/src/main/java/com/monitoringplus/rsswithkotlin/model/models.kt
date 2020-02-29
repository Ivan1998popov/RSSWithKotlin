package com.monitoringplus.rsswithkotlin.model


data class Feed(
    val author: String,
    val description: String,
    val image: String,
    val link: String,
    val title: String,
    val url: String
)

data class Item(
    val author: String,
    val categories: List<String>,
    val content: String,
    val description: String,
    val enclosure: Enclosure,
    val guid: String,
    val link: String,
    val pubDate: String,
    val thumbnail: String,
    val title: String
)

data class Enclosure(
    val link: String
)

data class RSSObject(
    val status: String,
    val feed: Feed,
    val items: List<Item>
)

