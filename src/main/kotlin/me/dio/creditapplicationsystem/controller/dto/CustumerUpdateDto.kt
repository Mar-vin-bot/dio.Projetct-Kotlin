package me.dio.creditapplicationsystem.controller.dto

import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entities.Custumer
import java.math.BigDecimal

class CustumerUpdateDto(
    @field:NotEmpty( message = "Invalid input firstName")val firstName : String,
    @field:NotEmpty( message = "Invalid input firstName")val lastName : String,
    @field:NotNull val income: BigDecimal,
    @field:NotEmpty( message = "Invalid input firstName")val zipCode : String,
    @field:NotEmpty( message = "Invalid input firstName")val street : String,

    ) {
    fun toEntity (custumer: Custumer): Custumer{
        custumer.firstName = this.firstName
        custumer.lastName = this.lastName
        custumer.income = this.income
        custumer.address.zipCode = this.zipCode
        custumer.address.street = this.street
        return custumer;
    }


}