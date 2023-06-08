package me.dio.creditapplicationsystem.controller

import jakarta.validation.Valid
import me.dio.creditapplicationsystem.controller.dto.CustumerDto
import me.dio.creditapplicationsystem.controller.dto.CustumerUpdateDto
import me.dio.creditapplicationsystem.controller.dto.CustumerView
import me.dio.creditapplicationsystem.entities.Custumer
import me.dio.creditapplicationsystem.service.imp.CustumerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/custumer")
class CustumerController(
    private val custumerService: CustumerService
) {

    @PostMapping
    fun saveCustumer(@RequestBody @Valid custumerDto: CustumerDto): ResponseEntity<String> {
        val savedCustumer = this.custumerService.save(custumerDto.toEntity());
        return ResponseEntity.status(HttpStatus.CREATED)
            .body("Custumer ${savedCustumer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById(@PathVariable id: Long): ResponseEntity<CustumerView> {
        val custumer = this.custumerService.findById(id)
        return ResponseEntity.status(HttpStatus.OK).body(CustumerView(custumer));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteCustumer(@PathVariable id: Long) = this.custumerService.delete(id)

    @PatchMapping
    fun updateCustumer(
        @RequestParam(value = "custumerId") id: Long,
        @RequestBody @Valid custumerUpdateDto: CustumerUpdateDto
    ): ResponseEntity<CustumerView> {
        val custumer = this.custumerService.findById(id)
        val custumerToUpdate: Custumer = custumerUpdateDto.toEntity(custumer)
        val custumerUpdated: Custumer = this.custumerService.save(custumerToUpdate)
        return ResponseEntity.status(HttpStatus.OK).body(CustumerView(custumerUpdated))
    }

}