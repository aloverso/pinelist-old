package com.pinelist.sqladapter

import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Item
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.railway.Failure
import com.pinelist.listpolicy.railway.Success
import com.pinelist.listpolicy.railway.TwoTrack
import org.springframework.data.repository.CrudRepository
import javax.persistence.CascadeType
import javax.persistence.Entity
import javax.persistence.FetchType
import javax.persistence.Id
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.OneToMany

@Entity(name = "pinelists")
data class PinelistEntity(
        @Id var id: String = "",
        var name: String = "",

        @OneToMany(cascade = [CascadeType.ALL], orphanRemoval = true, fetch = FetchType.EAGER)
        @JoinColumn(name = "pinelist_id")
        var items: MutableList<ItemEntity> = mutableListOf()
) {
    fun toPinelist(): Pinelist {
        return Pinelist(
                id = this.id,
                name = this.name,
                items = this.items.map { Item(id = it.itemId, name = it.name) }
        )
    }
}

@Entity(name = "items")
data class ItemEntity(
        @Id var itemId: String = "",
        var name: String = "",

        @ManyToOne(fetch = FetchType.LAZY)
        @JoinColumn(name = "pinelist_id", referencedColumnName = "id")
        var pinelist: PinelistEntity? = null
)

interface JpaPinelistRepository : CrudRepository<PinelistEntity, String>
interface JpaItemRepository : CrudRepository<ItemEntity, String>

class SqlPinelistRepository(
        val jpaPinelistRepository: JpaPinelistRepository,
        val jpaItemRepository: JpaItemRepository
) : PinelistRepository {
    override fun findById(id: String): TwoTrack<Pinelist> {
        val found = jpaPinelistRepository.findById(id)
        if (found.isPresent) {
            return Success(found.get().toPinelist())
        } else {
            return Failure(ErrorMessage.LIST_NOT_FOUND)
        }
    }

    override fun save(name: String): TwoTrack<Pinelist> {
        val entityToSave = PinelistEntity(
                id = generateRandomId(),
                name = name,
                items = mutableListOf()
        )
        jpaPinelistRepository.save(entityToSave)
        return Success(entityToSave.toPinelist())
    }

    override fun saveItem(addItemRequest: AddItemRequest): TwoTrack<Pinelist> {
        val matchingPinelist: PinelistEntity = jpaPinelistRepository.findById(addItemRequest.pinelistId).get()
        val entityToSave = ItemEntity(
                itemId = generateRandomId(),
                name = addItemRequest.name,
                pinelist = matchingPinelist
        )
        matchingPinelist.items.add(entityToSave)
        jpaPinelistRepository.save(matchingPinelist)
        return Success(matchingPinelist.toPinelist())
    }

    override fun deleteAll() {
        jpaItemRepository.deleteAll()
        jpaPinelistRepository.deleteAll()
    }

    private fun generateRandomId(length: Int = 8): String {
        val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')
        return (1..length)
                .map { kotlin.random.Random.nextInt(0, charPool.size) }
                .map(charPool::get)
                .joinToString("")
    }
}