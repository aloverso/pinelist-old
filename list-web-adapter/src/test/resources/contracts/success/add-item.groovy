package success

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should add an item to a list"
    request{
        method POST()
        headers { contentType(applicationJson()) }
        url "/api/list/12345/add"
        body(this.getClass().getResource("/add-item-request.json").text)
    }
    response {
        body(this.getClass().getResource("/add-item-response.json").text)
        status OK()
    }
}