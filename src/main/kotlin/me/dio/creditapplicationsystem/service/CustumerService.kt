package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Custumer
import java.util.*

interface CustumerService {

    fun save(custumer: Custumer): Custumer
    fun findById(id: Long ): Custumer
    fun delete(id: Long): Custumer

}