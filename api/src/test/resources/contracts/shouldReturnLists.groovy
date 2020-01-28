package contracts

org.springframework.cloud.contract.spec.Contract.make {
    request {
        method 'GET'
        url '/api/v1/lists'
    }
    response {
        status 200
        body '''
		[
            {
              "id": "123",
              "name": "my list"
            }, 
            {
              "id": "456", 
              "name": "my cool list"
            }
        ]
	'''
    }
}