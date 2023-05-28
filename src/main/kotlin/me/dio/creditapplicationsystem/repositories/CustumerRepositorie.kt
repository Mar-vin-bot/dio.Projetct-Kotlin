package me.dio.creditapplicationsystem.repositories


import me.dio.creditapplicationsystem.entities.Custumer
import org.springframework.data.jpa.repository.JpaRepository

interface CustumerRepositorie: JpaRepository<Custumer, Long> {
}