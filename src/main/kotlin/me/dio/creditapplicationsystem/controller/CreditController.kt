package me.dio.creditapplicationsystem.controller

import me.dio.creditapplicationsystem.controller.dto.CreditDto
import me.dio.creditapplicationsystem.controller.dto.CreditView
import me.dio.creditapplicationsystem.controller.dto.CreditViewList
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.service.imp.CreditService
import org.aspectj.apache.bcel.classfile.Code
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import java.util.*
import java.util.stream.Collectors

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


    @GetMapping
    fun findAllCustumerId(@RequestParam(value = "custumerId")
                          custumerId: Long) : List<CreditViewList>{
        return this.creditService.findAllByCustumer(custumerId)
            .stream().map{credit: Credit -> CreditViewList(credit)}.collect(Collectors.toList())
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(@RequestParam(value = "custumerId")
                         custumerId: Long, @PathVariable creditCode: UUID): CreditView {
    val credit : Credit = this.creditService.findByCreditCode(custumerId, creditCode)
    return CreditView(credit)
    }
}