package com.pinelist.webadapter

import io.restassured.module.mockmvc.RestAssuredMockMvc
import org.junit.Before
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner
import org.springframework.web.context.WebApplicationContext


@RunWith(SpringRunner::class)
@SpringBootTest
@ContextConfiguration(classes = [WebController::class])
abstract class ContractsBaseTest {

    @Autowired
    private lateinit var webAppContext: WebApplicationContext

    @Before
    fun setup() {
        RestAssuredMockMvc.webAppContextSetup(webAppContext)
    }
}