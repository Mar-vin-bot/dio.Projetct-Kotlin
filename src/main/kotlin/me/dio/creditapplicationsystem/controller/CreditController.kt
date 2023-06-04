package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.controller.dto.CreditDto
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.service.imp.CreditService
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api/credit")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
   fun saveCredit(@RequestBody creditDto: CreditDto): String{
    val credit: Credit = this.creditService.save(creditDto.toEntity())
   return "Credit ${credit.creditCode} - " +
           "Custumer ${credit.custumer?.firstName} salved"
   }


    @GetMapping("/{id}")
    fun findById(id: Long): Credit {
        val credit: Credit = this.creditService.findByCreditCode();
    }
}