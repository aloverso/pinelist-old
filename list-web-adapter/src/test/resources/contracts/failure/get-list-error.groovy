package failure

import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should error gracefully for get list items"
    request{
        method GET()
        url "/api/list/doesnotexist"
    }
    response {
        body(this.getClass().getResource("/get-list-error-response.json").text)
        status 500
    }
}