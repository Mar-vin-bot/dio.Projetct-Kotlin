package me.dio.creditapplicationsystem.repositories

import me.dio.creditapplicationsystem.entities.Credit
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CreditRepositorie: JpaRepository<Credit, Long> {
}