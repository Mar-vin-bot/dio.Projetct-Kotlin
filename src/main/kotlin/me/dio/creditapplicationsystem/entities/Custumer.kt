package me.dio.creditapplicationsystem.entities

import jakarta.persistence.*

@Entity
@Table(name="tb_custumer")
data class Custumer(
        @Column(nullable = false)
        var firstName: String = "",
        @Column(nullable = false)
        var lastName: String = "",
        @Column(nullable = false, unique = true)
        val cpf: String,
        @Column(nullable = false, unique = true)
        var email: String = "",
        @Column(nullable = false)
        var password: String = "",
        @Column(nullable = false)
        @Embedded
        var address: Address = Address(),
        @Column(nullable = false)
        @OneToMany(fetch = FetchType.LAZY, cascade = arrayOf(CascadeType.REMOVE, CascadeType.PERSIST),
                mappedBy = "custumer")
        var credits: List<Credit> = mutableListOf(),

        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        var id: Long? = null,
        )

