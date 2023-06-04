package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.controller.dto.CustumerDto
import me.dio.creditapplicationsystem.service.imp.CustumerService
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/custumer")
class CustumerController(
    private val custumerService: CustumerService
) {

    @PostMapping
    fun saveCustumer(@RequestBody custumerDto: CustumerDto) : String{
     val savedCustumer = this.custumerService.save(custumerDto.toEntity());
        return "Custumer ${savedCustumer.email} saved!"
    }

}