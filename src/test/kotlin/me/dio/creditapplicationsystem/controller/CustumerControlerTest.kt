package me.dio.creditapplicationsystem.controller

import com.fasterxml.jackson.databind.ObjectMapper
import me.dio.creditapplicationsystem.controller.dto.CustumerDto
import me.dio.creditapplicationsystem.controller.dto.CustumerUpdateDto
import me.dio.creditapplicationsystem.entities.Custumer
import me.dio.creditapplicationsystem.repositories.CustumerRepositorie
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.http.MediaType
import org.springframework.test.context.ActiveProfiles
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultHandlers
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import java.math.BigDecimal

@SpringBootTest
@ActiveProfiles("test")
@AutoConfigureMockMvc
@ContextConfiguration
class CustumerControlerTest {
    @Autowired
    private lateinit var custumerRepositorie: CustumerRepositorie

    @Autowired
    private lateinit var mockMvc: MockMvc

    @Autowired
    private lateinit var objectMapper: ObjectMapper

    companion object {
        const val URL: String = "/api/custumer"
    }

    @BeforeEach
    fun setup() = custumerRepositorie.deleteAll()

    @AfterEach
    fun tearDown() = custumerRepositorie.deleteAll()

    @Test
    fun shold_createCustumer_and_return201() {
        //give
        val custumerDTO: CustumerDto = builderCustumerDto()
        val custumerDto_toString: String = objectMapper.writeValueAsString(custumerDTO)
        //when
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL).contentType(MediaType.APPLICATION_JSON)
                .content(custumerDto_toString)
        )
            .andExpect(MockMvcResultMatchers.status().isCreated)
            .andDo(MockMvcResultHandlers.print())
        //then
    }

    @Test
    fun should_notSaveCustumer_sameCPF_andRerurn_404() {
        //given
        custumerRepositorie.save(builderCustumerDto().toEntity())
        val custumerDto: CustumerDto = builderCustumerDto()
        val custumerDto_toString: String = objectMapper.writeValueAsString(custumerDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(custumerDto_toString)
        )
            .andExpect(MockMvcResultMatchers.status().isConflict)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun should_notSaveCustUmer_withEmptyFirstName_andReturn_400() {
        //given
        val customerDto: CustumerDto = builderCustumerDto(firstName = "")
        val custumerDto_toString: String = objectMapper.writeValueAsString(customerDto)
        //when
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.post(URL)
                .content(custumerDto_toString)
                .contentType(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andExpect(MockMvcResultMatchers.jsonPath("$.title").value("Bad Request Consult the documentation"))
            .andExpect(MockMvcResultMatchers.jsonPath("$.timestamp").exists())
            .andExpect(MockMvcResultMatchers.jsonPath("$.status").value(400))
            .andExpect(
                MockMvcResultMatchers.jsonPath("$.exception")
                    .value("class org.springframework.web.bind.MethodArgumentNotValidException")
            )
            .andExpect(MockMvcResultMatchers.jsonPath("$.details[*]").isNotEmpty)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun should_findCustumerById_andReturn_200() {
        //give
        val custumer: Custumer = custumerRepositorie.save(builderCustumerDto().toEntity())
        //when
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/${custumer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun should_notFindCustumer_when_invalidId_return_400() {
        //given
        val invalidId: Long = 2L;
        //when
        mockMvc.perform(
            MockMvcRequestBuilders.get("$URL/$invalidId.id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
    }

    @Test
    fun should_deleteCustumerById_return204(){
        val custumer: Custumer = custumerRepositorie.save(builderCustumerDto().toEntity())
        //when
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/${custumer.id}")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isNoContent)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun should_deleteCustumerById_retun400(){
        val invalidId: Long = 2L;
        //when
        mockMvc.perform(
            MockMvcRequestBuilders.delete("$URL/$invalidId.id")
                .accept(MediaType.APPLICATION_JSON)
        )
            .andExpect(MockMvcResultMatchers.status().isBadRequest)
            .andDo(MockMvcResultHandlers.print())
    }

    @Test
    fun should_updateCustumer_andReturn200(){
        //give
        val custumer: Custumer = custumerRepositorie.save(builderCustumerDto().toEntity())
        val custumerUpdateDto: CustumerUpdateDto = builderCustumerUpdateDto()
        val custumerDto_toString: String = objectMapper.writeValueAsString(custumerUpdateDto)
        //then
        mockMvc.perform(
            MockMvcRequestBuilders.patch("$URL?custumerId=${custumer.id}")
                .contentType(MediaType.APPLICATION_JSON)
                .content(custumerDto_toString)
        )
            .andExpect(MockMvcResultMatchers.status().isOk)
            .andDo(MockMvcResultHandlers.print())
    }



    private fun builderCustumerDto(
        firstName: String = "João ",
        lastName: String = "Lancelotte",
        cpf: String = "28475934625",
        email: String = "joao@email.com",
        income: BigDecimal = BigDecimal.valueOf(1000.0),
        password: String = "1234",
        zipCode: String = "3000000",
        street: String = "Rua da palavra ",
    ) = CustumerDto(
        firstName = firstName,
        lastName = lastName,
        cpf = cpf,
        email = email,
        income = income,
        password = password,
        zipCode = zipCode,
        street = street
    )

    private fun builderCustumerUpdateDto(
        firstName: String = "Mario",
        lastName: String = "World",
        income: BigDecimal = BigDecimal.valueOf(5000.0),
        zipCode: String = "45656",
        street: String = "Rua Peach Cogumelo"
    ): CustumerUpdateDto = CustumerUpdateDto(
        firstName = firstName,
        lastName = lastName,
        income = income,
        zipCode = zipCode,
        street = street
    )
}