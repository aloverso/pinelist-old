package com.pinelist.listpolicy.ports.fakes

import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Item
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.railway.Failure
import com.pinelist.listpolicy.railway.Success
import com.pinelist.listpolicy.railway.TwoTrack
import kotlin.random.Random

class FakePinelistRepository: PinelistRepository {

    private var pinelists: MutableMap<String, Pinelist> = HashMap()

    override fun findAll(): TwoTrack<List<Pinelist>> {
        return Success(pinelists.values.toList())
    }

    override fun findById(id: String): TwoTrack<Pinelist> {
        val pinelist = pinelists[id] ?: return Failure(ErrorMessage.LIST_NOT_FOUND)
        return Success(pinelist)
    }

    override fun save(name: String): TwoTrack<Pinelist> {
        val pinelist = Pinelist(
                id = "some-id-"+ Random.nextInt(),
                name = name,
                items = emptyList()
        )
        pinelists[pinelist.id] = pinelist
        return Success(pinelist)
    }

    override fun saveItem(addItemRequest: AddItemRequest): TwoTrack<Pinelist> {
        val newItem = Item(name = addItemRequest.name, id = "some-item-id-"+Random.nextInt())
        val matchingPinelist = pinelists[addItemRequest.pinelistId]!!
        val newPinelist = matchingPinelist.copy(items = matchingPinelist.items + newItem)

        pinelists[addItemRequest.pinelistId] = newPinelist
        return Success(newPinelist)
    }

    override fun deleteAll() {
        pinelists = HashMap()
    }

    fun saveList(pinelist: Pinelist) {
        pinelists[pinelist.id] = pinelist
    }
}