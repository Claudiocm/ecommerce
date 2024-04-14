package com.claudio.gobots.ecommerce.exception

import com.claudio.gobots.ecommerce.exception.response.ErrorResponse
import lombok.extern.slf4j.Slf4j
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import javax.naming.ServiceUnavailableException

@Slf4j
@RestControllerAdvice
class GlobalExceptionHandler : ResponseEntityExceptionHandler() {

    @ExceptionHandler(BadRequestException::class)
    fun handleBadRequestException(exception: Exception): ResponseEntity<ErrorResponse> {

        val errorMessage = ErrorResponse("Error: BadRequest - ${exception.message}", status = HttpStatus.BAD_REQUEST.value())

        return ResponseEntity(errorMessage, HttpStatus.BAD_REQUEST)
    }

    @ExceptionHandler(NotFoundException::class)
    fun handleNotFoundException(exception: NotFoundException): ResponseEntity<ErrorResponse> {
        val errorMessage = ErrorResponse("Error: NotFoundException - ${exception.message}", HttpStatus.NOT_FOUND.value())

        return ResponseEntity(errorMessage, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(ServiceUnavailableException::class)
    fun handleServiceUnavailableException(exception: ServiceUnavailableException): ResponseEntity<ErrorResponse> {
        logger.error("Service Unavailable occurred: ${exception.message}")
        val errorResponse = ErrorResponse("Erro: Service Unavailable - ${exception.message}", HttpStatus.SERVICE_UNAVAILABLE.value())
        return ResponseEntity.status(HttpStatus.SERVICE_UNAVAILABLE).body(errorResponse)
    }

    @ExceptionHandler(InternalServerErrorException::class)
    fun handleInternalServerErrorException(exception: InternalServerErrorException): ResponseEntity<ErrorResponse> {
        logger.error("Internal ServerError occurred: ${exception.message}")
        val errorResponse = ErrorResponse("Erro: Internal Server Error - ${exception.message}", HttpStatus.INTERNAL_SERVER_ERROR.value())
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorResponse)
    }
}