package me.dio.creditapplicationsystem.service

import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import me.dio.creditapplicationsystem.entities.Address
import me.dio.creditapplicationsystem.entities.Custumer
import me.dio.creditapplicationsystem.repositories.CustumerRepositorie
import me.dio.creditapplicationsystem.service.imp.CustumerService
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal

@ActiveProfiles("teste")
@ExtendWith(MockKExtension::class)

class CustumerServiceTest {

    @MockK lateinit var custumerRepositorie: CustumerRepositorie
    @InjectMockKs lateinit var custumerService: CustumerService

    @Test
    fun should_create_custumer(){
        //given
        val fakeCustumer = buildCustumer()
        every { custumerRepositorie.save(any()) } returns fakeCustumer
        //when
        val actual: Custumer = custumerService.save(fakeCustumer)

        //then
        Assertions.assertThat(actual).isNotNull
        Assertions.assertThat(actual).isSameAs(fakeCustumer)
        verify(exactly = 1) { custumerRepositorie.save(fakeCustumer) }
    }



    companion object {
        fun buildCustumer(
            firstName: String = "Joao",
            lastName: String = "Lancelotte",
            cpf: String = "28475934123",
            email: String = "joao@gmail.com",
            password: String = "as123",
            zipCode: String = "12345",
            street: String = "Rua street",
            income: BigDecimal = BigDecimal.valueOf(1000.0),
            id: Long = 1L
        ) = Custumer(
            firstName = firstName,
            lastName = lastName,
            cpf = cpf,
            email = email,
            password = password,
            address = Address(
                zipCode = zipCode,
                street = street,
            ),
            income = income,
            id = id
        )
    }
}