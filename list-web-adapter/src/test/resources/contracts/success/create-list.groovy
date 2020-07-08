package success

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should create a new list"
    request{
        method POST()
        headers { contentType(applicationJson()) }
        url "/api/lists/create"
        body(this.getClass().getResource("/create-list-request.json").text)
    }
    response {
        body(this.getClass().getResource("/create-list-response.json").text)
        status OK()
    }
}