package com.pinelist

import arrow.core.Either
import com.pinelist.listpolicy.AddItem
import com.pinelist.listpolicy.CreateList
import com.pinelist.listpolicy.FindAll
import com.pinelist.listpolicy.FindList
import com.pinelist.listpolicy.addItemFactory
import com.pinelist.listpolicy.models.Pinelist
import com.pinelist.listpolicy.ports.PinelistRepository
import com.pinelist.listpolicy.railway.convertToTwoTrack
import com.pinelist.sqladapter.JpaItemRepository
import com.pinelist.sqladapter.JpaPinelistRepository
import com.pinelist.sqladapter.SqlPinelistRepository
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean

@SpringBootApplication
class PinelistApplication {

	@Bean
	fun getPinelistRepository(
			jpaPinelistRepository: JpaPinelistRepository,
			jpaItemRepository: JpaItemRepository
	): PinelistRepository {
		return SqlPinelistRepository(jpaPinelistRepository, jpaItemRepository)
	}

	@Bean
	@Qualifier("createList")
	fun getCreateList(pinelistRepository: PinelistRepository): CreateList {
		return convertToTwoTrack { name: String -> pinelistRepository.save(name)}
	}

	@Bean
	@Qualifier("addItem")
	fun getAddItem(pinelistRepository: PinelistRepository): AddItem {
		return convertToTwoTrack(addItemFactory(
				pinelistRepository = pinelistRepository
		))
	}

	@Bean
	@Qualifier("findList")
	fun getFindList(
			pinelistRepository: PinelistRepository
	): FindList {
		return convertToTwoTrack { id: String -> pinelistRepository.findById(id)}
	}

	@Bean
	@Qualifier("findAll")
	fun findAllStub(pinelistRepository: PinelistRepository): FindAll {
		return { pinelistRepository.findAll() }
	}
}

fun main(args: Array<String>) {
	runApplication<PinelistApplication>(*args)
}