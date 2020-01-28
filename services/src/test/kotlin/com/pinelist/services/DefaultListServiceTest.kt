package com.pinelist.services

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.stubs.StubListRepository
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test

class DefaultListServiceTest {

    lateinit var service: DefaultListService
    lateinit var stubListRepository: StubListRepository

    @Before
    fun setUp() {
        stubListRepository = StubListRepository()
        service = DefaultListService(stubListRepository)
    }

    @Test
    fun `getLists() gets pinelists from list repository`() {
        stubListRepository.stubbedPineLists = listOf(
                PineList(
                        name = "My List",
                        id = "123"
                )
        )

        val pinelists = service.getLists()
        assertThat(pinelists.size).isEqualTo(1)
        assertThat(pinelists[0].name).isEqualTo("My List")
        assertThat(pinelists[0].id).isEqualTo("123")
    }

    @Test
    fun `addList() saves list in repository`() {
        service.addList("some new list")
        assertThat(stubListRepository.save_calledWith).isEqualTo("some new list")
    }

}