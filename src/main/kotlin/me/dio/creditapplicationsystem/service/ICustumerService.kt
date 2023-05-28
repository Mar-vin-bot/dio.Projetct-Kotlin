package me.dio.creditapplicationsystem.service

import me.dio.creditapplicationsystem.entities.Custumer

interface ICustumerService {

    fun save(custumer: Custumer): Custumer
    fun findById(id: Long ): Custumer
    fun delete(id: Long)

}