package com.pinelist.integrations

import org.assertj.core.api.Assertions.assertThat
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@DataJpaTest
class IntegrationTest {

    @Autowired
    lateinit var jpaListRepository: JpaListRepository

    lateinit var listRepository: DefaultListRepository

    @Before
    fun setUp() {
        listRepository = DefaultListRepository(jpaListRepository)
    }

    @Test
    fun canGetLists() {
        listRepository.save("test list")

        listRepository.save("other test list")

        val resultLists = listRepository.getLists()
        assertThat(resultLists.size).isEqualTo(2)
        assertThat(resultLists[0].name).isEqualTo("test list")
        assertThat(resultLists[1].name).isEqualTo("other test list")
        assertThat(resultLists[0].id).isNotEqualTo(resultLists[1].id)
    }
}