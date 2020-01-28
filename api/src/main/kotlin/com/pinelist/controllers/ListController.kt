package com.pinelist.controllers

import com.pinelist.domain.pinelist.PineList
import com.pinelist.domain.pinelist.ListService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class ListController(val listService: ListService) {

    @GetMapping("/api/v1/lists")
    fun getLists(): List<PineList> {
        return listService.getLists()
    }

    @PostMapping("/api/v1/lists")
    fun addList(@RequestBody listRequest: ListRequest) {
        listService.addList(listRequest.name)
    }
}

data class ListRequest(
        val name: String
)



