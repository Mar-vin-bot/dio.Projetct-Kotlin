package me.dio.creditapplicationsystem.entities

import jakarta.persistence.*
import me.dio.creditapplicationsystem.entities.enumeric.Status
import java.math.BigDecimal
import java.time.LocalDate
import java.util.*
@Entity
@Table(name="tb_credit")
data class Credit(
        @Column(nullable = false, unique = true)
        var creditCode: UUID = UUID.randomUUID(),
        @Column(nullable = false)
        val creditValue: BigDecimal = BigDecimal.ZERO,
        @Column(nullable = false)
        val dayFirstInstallment: LocalDate,
        @Column(nullable = false)
        val numberOfInstallment: Int = 0,

        @Enumerated
        val status: Status = Status.IN_PROGRESS,

        @ManyToOne
        var custumer: Custumer? = null,

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        val id: Long? = null
)
