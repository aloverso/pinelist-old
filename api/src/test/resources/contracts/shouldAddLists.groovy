package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'POST'
        url '/api/v1/lists'
        headers {
            header 'Content-Type': 'application/json'
        }
        body '''
		{
          "name": "bond list"
        }
	'''
    }
    response {
        status 200
    }
}