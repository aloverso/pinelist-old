package com.pinelist.listpolicy

import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.railway.TwoTrack
import com.pinelist.listpolicy.railway.convertToTwoTrack
import com.pinelist.listpolicy.railway.pipe

fun addItemFactory(
        pinelistRepository: PinelistRepository
): (AddItemRequest) -> TwoTrack<Pinelist> {
    return { addItemRequest: AddItemRequest ->
        validateAddItemRequestFactory(pinelistRepository)(addItemRequest)
                .pipe(convertToTwoTrack { req: AddItemRequest -> pinelistRepository.saveItem(req) })
    }
}

fun validateAddItemRequestFactory(
        pinelistRepository: PinelistRepository
): (AddItemRequest) -> TwoTrack<AddItemRequest> {
    return { addItemRequest: AddItemRequest ->
        pinelistRepository.findById(addItemRequest.pinelistId)
                .map { addItemRequest }
                .mapLeft { ErrorMessage.CANNOT_ADD_ITEM_TO_NONEXISTENT_LIST }
    }
}

data class AddItemRequest(
        val name: String,
        val pinelistId: String
)