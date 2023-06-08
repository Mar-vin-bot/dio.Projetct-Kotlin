package me.dio.creditapplicationsystem.controller.dto

import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Custumer
import java.math.BigDecimal
import java.time.LocalDate

class CreditDto(
    @field:NotNull val creditValue: BigDecimal,
    @field:Future val dayFirstOfInstallment : LocalDate,
    val numberOfInstallment: Int,
    @field:NotNull val custumerId : Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallment,
        custumer = Custumer(id = this.custumerId)
    )

}