package com.pinelist;

import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = PinelistApplication.class)
@AutoConfigureMockMvc
@ActiveProfiles("stub-integrations")
public abstract class ContractVerifierBase {

    @Autowired
    public WebApplicationContext webApplicationContext;

    @Before
    public void setUp() {
        RestAssuredMockMvc.webAppContextSetup(webApplicationContext);
    }
}