package com.pinelist.listpolicy.ports

import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.railway.TwoTrack

interface PinelistRepository {
    fun findAll(): TwoTrack<List<Pinelist>>
    fun findById(id: String): TwoTrack<Pinelist>
    fun save(name: String): TwoTrack<Pinelist>
    fun saveItem(addItemRequest: AddItemRequest): TwoTrack<Pinelist>
    fun deleteAll()
}