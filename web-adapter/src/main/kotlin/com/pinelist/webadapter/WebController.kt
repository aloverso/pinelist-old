package com.pinelist.webadapter;

import com.pinelist.listpolicy.sayHello
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
class WebController {

    @GetMapping("/hello")
    fun hello(): String {
        return sayHello()
    }
}
