package com.pinelist.services

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListRepository
import com.pinelist.domain.pinelist.ListService

class DefaultListService(val listRepository: ListRepository) : ListService {
    override fun addList(name: String) {
        listRepository.save(name)
    }

    override fun getLists(): List<PineList> {
        return listRepository.getLists()
    }
}
