package br.com.samuca.lojalivros.validation

import org.springframework.context.MessageSource
import org.springframework.context.i18n.LocaleContextHolder
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice
class ValidationExceptionHandler(val messageSource: MessageSource) {

    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationException(ex: MethodArgumentNotValidException): ResponseEntity<Map<String, List<String>>> {
        val fieldErrors = ex.bindingResult.fieldErrors
        val errorsMap = fieldErrors.groupBy({ it.field }, { resolveMessage(it) })
        return ResponseEntity(errorsMap, HttpStatus.BAD_REQUEST)
    }

    private fun resolveMessage(fieldError: FieldError): String {
        val locale = LocaleContextHolder.getLocale()
        return messageSource.getMessage(fieldError, locale)
    }

    @ExceptionHandler(ValidationException::class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleCustomValidationException(ex: ValidationException): ResponseEntity<List<String>> {
        return ResponseEntity(ex.errors, HttpStatus.BAD_REQUEST)
    }
}
