package com.pinelist.listpolicy.models

data class Pinelist(
        val id: String,
        val name: String,
        val items: List<Item>
)

data class Item(
        val id: String,
        val name: String
)