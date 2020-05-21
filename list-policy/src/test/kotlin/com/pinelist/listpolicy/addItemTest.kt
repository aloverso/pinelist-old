package com.pinelist.listpolicy

import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.fakes.FakePinelistRepository
import com.pinelist.listpolicy.ports.utils.assertFailure
import com.pinelist.listpolicy.ports.utils.buildPinelist
import com.pinelist.listpolicy.ports.utils.getSuccess
import com.pinelist.listpolicy.railway.TwoTrack
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class AddItemTest {

    lateinit var addItem: (AddItemRequest) -> TwoTrack<Pinelist>
    lateinit var fakePinelistRepository: FakePinelistRepository

    @BeforeEach
    fun setUp() {
        fakePinelistRepository = FakePinelistRepository()
        addItem = addItemFactory(
                pinelistRepository = fakePinelistRepository
        )
    }

    @Test
    fun `saves an item successfully and returns the updated pinelist`() {
        val initialPinelist = buildPinelist(items = emptyList())
        fakePinelistRepository.saveList(initialPinelist)

        val updatedPinelist = getSuccess(addItem(AddItemRequest(
                name = "some item",
                pinelistId = initialPinelist.id
        )))

        assertThat(updatedPinelist.id).isEqualTo(initialPinelist.id)
        assertThat(updatedPinelist.name).isEqualTo(initialPinelist.name)
        assertThat(updatedPinelist.items.size).isEqualTo(1)
        assertThat(updatedPinelist.items[0].name).isEqualTo("some item")
    }

    @Test
    fun `fails with Error Message when matching list does not exist`() {
        val result = addItem(AddItemRequest(
                name = "some item",
                pinelistId = "does-not-exist"
        ))

        assertFailure(result, ErrorMessage.CANNOT_ADD_ITEM_TO_NONEXISTENT_LIST)
    }
}