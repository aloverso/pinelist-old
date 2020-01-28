package com.pinelist.controllers

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListService
import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.springframework.http.MediaType
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*
import org.springframework.test.web.servlet.result.MockMvcResultMatchers.*
import org.springframework.test.web.servlet.setup.MockMvcBuilders

class ListControllerTest {

    lateinit var stubListService: StubListService
    lateinit var client: MockMvc

    @Before
    fun setUp() {
        stubListService = StubListService()
        client = MockMvcBuilders.standaloneSetup(ListController(stubListService)).build()
    }

    @Test
    fun `should return list of pinelists`() {
        stubListService.stubbedGetLists = listOf(
                PineList(
                        name = "some name",
                        id = "some id"
                )
        )

        client.perform(get("/api/v1/lists"))
                .andExpect(status().is2xxSuccessful)
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("some name"))
                .andExpect(jsonPath("$[0].id").value("some id"))
    }

    @Test
    fun `should add a pinelist`() {
        client.perform(
                post("/api/v1/lists")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                            {
                                "name": "some new name"
                            }
                        """.trimIndent())
        )
                .andExpect(status().is2xxSuccessful)

        assertThat(stubListService.addList_nameCalledWith).isEqualTo("some new name")
    }
}

class StubListService: ListService {
    lateinit var stubbedGetLists: List<PineList>
    var addList_nameCalledWith: String? = null

    override fun getLists(): List<PineList> {
        return stubbedGetLists
    }

    override fun addList(name: String) {
        addList_nameCalledWith = name
    }
}
