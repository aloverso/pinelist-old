package com.pinelist.listpolicy.ports.contracts

import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.models.ErrorMessage
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.ports.utils.assertFailure
import com.pinelist.listpolicy.ports.utils.assertSuccess
import com.pinelist.listpolicy.ports.utils.getSuccess
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

abstract class PinelistRepositoryTest {

    lateinit var repository: PinelistRepository

    @BeforeEach
    fun setUp() {
        repository = getPinelistRepository()
        repository.deleteAll()
    }

    abstract fun getPinelistRepository(): PinelistRepository

    @Test
    fun `saves a pinelist by name and looks it up by its id`() {
        val savedPinelist = getSuccess(repository.save("some name"))
        assertSuccess(repository.findById(savedPinelist.id), Pinelist(
                id = savedPinelist.id,
                name = "some name",
                items = emptyList()
        ))
    }

    @Test
    fun `fails with ErrorMessage if list cannot be found`() {
        assertFailure(repository.findById("does not exist"), ErrorMessage.LIST_NOT_FOUND)
    }

    @Test
    fun `saves an item by name and finds it included in the list`() {
        val savedPinelist = getSuccess(repository.save("some name"))
        val savedPinelistWithItem = getSuccess(repository.saveItem(AddItemRequest(name="some item name", pinelistId = savedPinelist.id)))
        val foundPinelist = getSuccess(repository.findById(savedPinelist.id))

        assertThat(savedPinelistWithItem.items[0].name).isEqualTo("some item name")
        assertThat(foundPinelist.items[0].name).isEqualTo("some item name")
    }
}