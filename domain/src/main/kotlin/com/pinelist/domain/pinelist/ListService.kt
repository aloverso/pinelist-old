package com.pinelist.domain.pinelist

interface ListService {
    fun getLists(): List<PineList>
    fun addList(name: String)
}