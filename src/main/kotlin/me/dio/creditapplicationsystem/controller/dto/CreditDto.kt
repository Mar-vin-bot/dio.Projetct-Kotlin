package me.dio.creditapplicationsystem.controller.dto

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Custumer
import java.math.BigDecimal
import java.time.LocalDate

class CreditDto(
    val creditValue: BigDecimal,
    val dayFirstOfInstallment : LocalDate,
    val numberOfInstallment: Int,
    val custumerId : Long
) {

    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstOfInstallment,
        numberOfInstallment = this.numberOfInstallment,
        custumer = Custumer(id = this.custumerId)
    )

}