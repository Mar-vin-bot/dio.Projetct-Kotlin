package me.dio.creditapplicationsystem.repository



import me.dio.creditapplicationsystem.entities.Address
import me.dio.creditapplicationsystem.entities.Credit
import me.dio.creditapplicationsystem.entities.Custumer
import me.dio.creditapplicationsystem.repositories.CreditRepositorie
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.ActiveProfiles
import java.math.BigDecimal
import java.time.LocalDate
import java.time.Month
import java.util.*

@ActiveProfiles("test")
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
class CreditRepositoryTest {
    @Autowired lateinit var creditRepositorie: CreditRepositorie
    @Autowired lateinit var testEntityManager: TestEntityManager

    private lateinit var custumer: Custumer
    private lateinit var credit1: Credit
    private lateinit var credit2: Credit

    @BeforeEach fun setup () {
        custumer = testEntityManager.persist((buildCustumer()))
        credit1 = testEntityManager.persist(buildCredit(customer = custumer))
        credit2 = testEntityManager.persist(buildCredit(customer = custumer))
    }

    @Test
    fun `should find credit by credit code`() {
        //given
        val creditCode1 = UUID.fromString("aa547c0f-9a6a-451f-8c89-afddce916a29")
        val creditCode2 = UUID.fromString("49f740be-46a7-449b-84e7-ff5b7986d7ef")
        credit1.creditCode = creditCode1
        credit2.creditCode = creditCode2
        //when
        val fakeCredit1: Credit = creditRepositorie.findByCreditCode(creditCode1)!!
        val fakeCredit2: Credit = creditRepositorie.findByCreditCode(creditCode2)!!
        //then
        Assertions.assertThat(fakeCredit1).isNotNull
        Assertions.assertThat(fakeCredit2).isNotNull
        Assertions.assertThat(fakeCredit1).isSameAs(credit1)
        Assertions.assertThat(fakeCredit2).isSameAs(credit2)
    }

    @Test
    fun should_findAllCredits_by_custumerId(){
        //give
        val custumerId: Long = 1L
        //when
        val creditList: List<Credit> = creditRepositorie.findAllByCustumer(custumerId)
        //then
        Assertions.assertThat(creditList).isNotEmpty
        Assertions.assertThat(creditList.size).isEqualTo(2)
        Assertions.assertThat(creditList).contains(credit1, credit2)
    }







    private fun buildCredit(
        creditValue: BigDecimal = BigDecimal.valueOf(500.0),
        dayFirstInstallment: LocalDate = LocalDate.of(2023, Month.APRIL, 22),
        numberOfInstallment: Int = 5,
        customer: Custumer
    ): Credit = Credit(
        creditValue = creditValue,
        dayFirstInstallment = dayFirstInstallment,
        numberOfInstallment = numberOfInstallment,
        custumer = customer
    )
    private fun buildCustumer(
        firstName: String = "João",
        lastName: String = "Lancelotte",
        cpf: String = "28475934625",
        email: String = "String@gmail.com",
        password: String = "12345",
        zipCode: String = "12345",
        street: String = "Rua da palavra",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
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
    )

}