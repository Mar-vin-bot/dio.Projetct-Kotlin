package me.dio.creditapplicationsystem.controller.dto

import me.dio.creditapplicationsystem.entities.Credit
import java.math.BigDecimal
import java.util.*

class CreditViewList(
    val creditCode: UUID,
    val creditValue: BigDecimal,
    val numberOfInstallments: Int

) {
    constructor(credit: Credit) : this(
        creditCode = credit.creditCode,
        creditValue = credit.creditValue,
        numberOfInstallments = credit.numberOfInstallment
    )
}
