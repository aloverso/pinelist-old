package com.pinelist.domain.pinelist

interface ListRepository {
    fun getLists(): List<PineList>
    fun save(name: String): PineList
}
