package com.pinelist.domain.pinelist.stubs

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListRepository

class StubListRepository : ListRepository {
    var save_calledWith: String? = null
    lateinit var stubbedPineLists: List<PineList>

    override fun getLists(): List<PineList> {
        return stubbedPineLists
    }

    override fun save(name: String): PineList {
        save_calledWith = name
        return PineList(name = name, id = "1")
    }
}