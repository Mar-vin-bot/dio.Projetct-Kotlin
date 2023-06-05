package me.dio.creditapplicationsystem.controller.dto

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.enumeric.Status
import java.math.BigDecimal
import java.util.*

data class CreditView(
    val creditCode : UUID,
    val creditValue : BigDecimal,
    val numberOfInstallments : Int,
    val status : Status,
    val emailCustumer : String?,
    val incomeCustumer : BigDecimal?
) {
    constructor(credit: Credit): this(
     creditCode = credit.creditCode,
     creditValue = credit.creditValue,
     numberOfInstallments = credit.numberOfInstallment,
     status = credit.status,
     emailCustumer = credit.custumer?.email,
     incomeCustumer = credit.custumer?.income,
    )

}
