package com.pinelist.webadapter;

import com.pinelist.listpolicy.AddItem
import com.pinelist.listpolicy.AddItemRequest
import com.pinelist.listpolicy.CreateList
import com.pinelist.listpolicy.FindAll
import com.pinelist.listpolicy.FindList
import com.pinelist.listpolicy.railway.*
import org.springframework.beans.factory.annotation.Qualifier
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


@RestController
@RequestMapping("/api/lists")
class ListController(
        @Qualifier("findList") val findList: FindList,
        @Qualifier("findAll") val findAll: FindAll,
        @Qualifier("createList") val createList: CreateList,
        @Qualifier("addItem") val addItem: AddItem
) {

    @GetMapping("")
    fun getAllListsEndpoint(): ResponseEntity<Response<*>> {
        return when (val allLists = findAll()) {
            is Success -> ResponseEntity
                    .status(200)
                    .body(Response(
                            outcome = Outcome.SUCCESS,
                            data = allLists.b.map { PinelistOverviewResponse(name = it.name, id = it.id) }
                    ))
            is Failure -> ResponseEntity
                    .status(500)
                    .body(Response(
                            outcome = Outcome.ERROR,
                            data = allLists.a.toString()
                    ))
        }
    }

    @PostMapping("/create")
    fun createListEndpoint(@RequestBody body: CreateRequestBody): ResponseEntity<Response<*>> {
        return when (val listDetails = createList(input(body.name))) {
            is Success -> ResponseEntity
                    .status(200)
                    .body(Response(
                            outcome = Outcome.SUCCESS,
                            data = listDetails.b
                    ))
            is Failure -> ResponseEntity
                    .status(500)
                    .body(Response(
                            outcome = Outcome.ERROR,
                            data = listDetails.a.toString()
                    ))
        }
    }

    @GetMapping("/{id}")
    fun getListEndpoint(@PathVariable id: String): ResponseEntity<Response<*>> {
        return when (val listDetails = findList(input(id))) {
            is Success -> ResponseEntity
                    .status(200)
                    .body(Response(
                            outcome = Outcome.SUCCESS,
                            data = listDetails.b
                    ))
            is Failure -> ResponseEntity
                    .status(500)
                    .body(Response(
                            outcome = Outcome.ERROR,
                            data = listDetails.a.toString()
                    ))
        }
    }

    @PostMapping("/{id}/add")
    fun addItemEndpoint(@PathVariable id: String, @RequestBody body: AddItemBody): ResponseEntity<Response<*>> {
        val addItemRequest = AddItemRequest(name = body.name, pinelistId = id)
        return when (val listDetails = addItem(input(addItemRequest))) {
            is Success -> ResponseEntity
                    .status(200)
                    .body(Response(
                            outcome = Outcome.SUCCESS,
                            data = listDetails.b
                    ))
            is Failure -> ResponseEntity
                    .status(500)
                    .body(Response(
                            outcome = Outcome.ERROR,
                            data = listDetails.a.toString()
                    ))
        }
    }
}


data class Response<T>(
        var outcome: Outcome,
        var data: T?
)

data class CreateRequestBody(
        var name: String
)

data class AddItemBody(
        var name: String
)

data class PinelistOverviewResponse(
        var id: String,
        var name: String
)

enum class Outcome {
    SUCCESS,
    ERROR
}
