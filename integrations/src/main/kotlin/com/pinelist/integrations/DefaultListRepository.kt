package com.pinelist.integrations

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListRepository
import org.springframework.web.client.RestOperations

class DefaultListRepository(
    val jpaListRepository: JpaListRepository
): ListRepository {
    override fun getLists(): List<PineList> {

        return jpaListRepository.findAll().map {
            it.toDomain()
        }
    }


    override fun save(name: String): PineList {
        return jpaListRepository.save(JpaPineList(
                name = name
        )).toDomain()
    }
}
