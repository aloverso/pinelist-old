import org.springframework.cloud.contract.spec.Contract
Contract.make {
    description "should say hello"
    request{
        method GET()
        url "/api/hello"
    }
    response {
        body("hello world")
        status OK()
    }
}