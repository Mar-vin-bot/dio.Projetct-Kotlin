package me.dio.creditapplicationsystem.controller.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import me.dio.creditapplicationsystem.entities.Address
import me.dio.creditapplicationsystem.entities.Custumer
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

class CustumerDto(
   @field:NotEmpty( message = "Invalid input firstName") val firstName: String,
   @field:NotEmpty( message = "Invalid input lastName")val lastName: String,
   @field:CPF( message = "CPF INVALIDO")val cpf: String,
   @field:NotNull val income: BigDecimal,
   @field:Email( message = "Invalid input email")val email: String,
   @field:NotEmpty( message = "Invalid input password")val password: String,
   @field:NotEmpty( message = "Invalid input zipCode")val zipCode: String,
   @field:NotEmpty( message = "Invalid input street")val street: String,
) {

    fun toEntity() : Custumer = Custumer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address (
            zipCode = this.zipCode,
            street = this.street,
        )


    )




}
