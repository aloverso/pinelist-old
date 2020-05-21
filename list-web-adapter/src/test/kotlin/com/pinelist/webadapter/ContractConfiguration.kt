package com.pinelist.webadapter

import com.pinelist.listpolicy.AddItem
import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.CreateList
import com.pinelist.listpolicy.FindList
import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Item
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.railway.Failure
import com.pinelist.listpolicy.railway.Success
import com.pinelist.listpolicy.railway.convertToTwoTrack
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile

@Configuration
@Profile("contract")
class ContractConfiguration {
    @Bean
    @Qualifier("findList")
    fun findListStub(): FindList {
        return convertToTwoTrack { id: String ->
            if (id == "doesnotexist") {
                Failure(ErrorMessage.LIST_NOT_FOUND)
            } else {
                Success(Pinelist(
                        id = id,
                        name = "My Cool Birthday List",
                        items = listOf(
                                Item(id = "1", name = "socks"),
                                Item(id = "2", name = "coffee")
                        )
                ))
            }
        }
    }

    @Bean
    @Qualifier("createList")
    fun createListStub(): CreateList {
        return convertToTwoTrack { name: String ->
            Success(Pinelist(
                    id = "12345",
                    name = name,
                    items = emptyList()
            ))
        }
    }

    @Bean
    @Qualifier("addItem")
    fun addItemStub(): AddItem {
        return convertToTwoTrack { addItemRequest: AddItemRequest ->
            Success(Pinelist(
                    id = addItemRequest.pinelistId,
                    name = "My Cool Birthday List",
                    items = listOf(
                            Item(id = "3", name = addItemRequest.name)
                    )
            ))
        }
    }
}
