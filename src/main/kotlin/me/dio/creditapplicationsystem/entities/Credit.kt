package me.dio.creditapplicationsystem.entities

import me.dio.creditapplicationsystem.entities.enumeric.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*

data class Credit(
        val creditCode: UUID = UUID.randomUUID(),
        val creditValue: BigDecimal = BigDecimal.ZERO,
        val dayFirstInstallment: LocalDate,
        val numberOfInstallment: Int = 0,
        val status: Status = Status.IN_PROGRESS,
        val custumer: Custumer? = null,
        val id: Long? = null
)
