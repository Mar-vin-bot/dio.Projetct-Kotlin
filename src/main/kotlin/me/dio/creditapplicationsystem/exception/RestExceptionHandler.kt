package me.dio.creditapplicationsystem.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.validation.ObjectError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.time.LocalDate
import java.util.Objects

//calsse define tratamento de exceções, fica escutando o controller
@RestControllerAdvice
class RestExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handlerValidExecption(ex: MethodArgumentNotValidException): ResponseEntity<ExceptionDetails> {
        val erros: MutableMap<String, String?> = HashMap()
        ex.bindingResult.allErrors.stream().forEach { erro: ObjectError ->
            val fildName: String = (erro as FieldError).field
            val mgnErro: String? = erro.defaultMessage
            erros[fildName] = mgnErro
        }
            return ResponseEntity(
                ExceptionDetails(
                    title = "Bad Request Consult the documentation",
                    timestamp = LocalDate.now(),
                    status = HttpStatus.BAD_REQUEST.value(),
                    exception = ex.javaClass.toString(),
                    details = erros
                ), HttpStatus.BAD_REQUEST
            )

        }
    }
