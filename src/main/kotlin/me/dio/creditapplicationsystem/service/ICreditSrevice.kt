package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entities.Credit
import java.util.*

interface ICreditSrevice {

    fun save(credit: Credit): Credit
    fun findAllByCustumer(custumerId: Long): List<Credit>
    fun findByCreditCode(custumerId: Long, creditCode: UUID): Credit

}