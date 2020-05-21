package com.pinelist.listpolicy.ports.fakes

import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.ports.contracts.PinelistRepositoryTest

class FakePinelistRepositoryTest: PinelistRepositoryTest() {

    override fun getPinelistRepository(): PinelistRepository {
        return FakePinelistRepository()
    }
}