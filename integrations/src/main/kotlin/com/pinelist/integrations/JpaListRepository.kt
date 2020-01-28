package com.pinelist.integrations

import com.pinelist.domain.pinelist.PineList
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

interface JpaListRepository: JpaRepository<JpaPineList, Long>

@Entity
class JpaPineList(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long = 0,

        val name: String = ""
) {
    fun toDomain(): PineList {
        return PineList(
                name = name,
                id = id.toString()
        )
    }
}