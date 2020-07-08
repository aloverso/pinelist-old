package success

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should get list items"
    request{
        method GET()
        url "/api/lists/12345"
    }
    response {
        body(this.getClass().getResource("/get-list-response.json").text)
        status OK()
    }
}