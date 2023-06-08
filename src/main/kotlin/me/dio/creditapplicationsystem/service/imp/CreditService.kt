package me.dio.creditapplicationsystem.service.imp

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.exception.BusinesException
import me.dio.creditapplicationsystem.repositories.CreditRepositorie
import me.dio.creditapplicationsystem.service.ICreditSrevice
import org.springframework.stereotype.Service
import java.lang.IllegalArgumentException

import java.lang.RuntimeException
import java.util.*

@Service
class CreditService(

    private val creditRepo: CreditRepositorie,
    private val custumerService: CustumerService

) : ICreditSrevice {
    override fun save(credit: Credit): Credit {
        credit.apply {
            custumer = custumerService.findById(credit.custumer?.id!!
            )
        }
        return this.creditRepo.save(credit)
    }

    override fun findAllByCustumer(custumerId: Long): List<Credit> {
    return this.creditRepo.findAllByCustumer(custumerId)
    }

    override fun findByCreditCode(custumerId: Long, creditCode: UUID): Credit {
        val  credit: Credit = this.creditRepo.findByCreditCode(creditCode) ?:
        throw BusinesException("CreditCode $creditCode not foud ")
        return if(credit.custumer?.id == custumerId) credit
            else throw IllegalArgumentException("Contact admin")
    }
}