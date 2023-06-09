package me.dio.creditapplicationsystem.service.imp

import me.dio.creditapplicationsystem.entities.Custumer
import me.dio.creditapplicationsystem.exception.BusinesException
import me.dio.creditapplicationsystem.repositories.CustumerRepositorie
import me.dio.creditapplicationsystem.service.ICustumerService
import org.springframework.stereotype.Service

@Service
class CustumerService(
    private val custumerRepo: CustumerRepositorie
): ICustumerService {

    override fun save(custumer: Custumer): Custumer =
        this.custumerRepo.save(custumer)

    override fun findById(id: Long): Custumer =
        this.custumerRepo.findById(id).orElseThrow{
            throw BusinesException("id $id not found")
        }


    override fun delete(id: Long) {
        val customer: Custumer = this.findById(id)
        this.custumerRepo.delete(customer)
    }
}