package success

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should get all lists"
    request{
        method GET()
        url "/api/lists"
    }
    response {
        body(this.getClass().getResource("/get-all-lists-response.json").text)
        status OK()
    }
}