package me.dio.creditapplicationsystem.controller

import jakarta.validation.Valid
import me.dio.creditapplicationsystem.controller.dto.CreditDto
import me.dio.creditapplicationsystem.controller.dto.CreditView
import me.dio.creditapplicationsystem.controller.dto.CreditViewList
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.service.imp.CreditService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.*
import java.util.stream.Collectors

@RestController
@RequestMapping("/api/credit")
class CreditController(
    private val creditService: CreditService
) {
    @PostMapping
    fun saveCredit(@RequestBody @Valid creditDto: CreditDto): ResponseEntity<String> {
        val credit: Credit = this.creditService.save(creditDto.toEntity())
        return ResponseEntity.status(HttpStatus.CREATED).body(
            "Credit ${credit.creditCode} - " +
                    "Custumer ${credit.custumer?.firstName} salved"
        )
    }


    @GetMapping
    fun findAllByCustumerId(
        @RequestParam(value = "custumerId")
        custumerId: Long
    ): ResponseEntity<List<CreditViewList>> {
        val creditViewList: List<CreditViewList> =
            this.creditService.findAllByCustumer(custumerId)
                .stream().map { credit: Credit ->
                    CreditViewList(credit)
                }.collect(Collectors.toList())
        return ResponseEntity.status(HttpStatus.OK).body(creditViewList)
    }

    @GetMapping("/{creditCode}")
    fun findByCreditCode(
        @RequestParam(value = "custumerId")
        custumerId: Long, @PathVariable creditCode: UUID
    ): ResponseEntity<CreditView> {
        val credit: Credit = this.creditService.findByCreditCode(custumerId, creditCode)
        return ResponseEntity.status(HttpStatus.OK).body(CreditView(credit))
    }
}