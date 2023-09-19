package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovaCompraRequest
import br.com.samuca.lojalivros.validation.EstadoPaisValidator
import jakarta.validation.Valid
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompraRestController(private val estadoPaisValidator: EstadoPaisValidator) {

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(estadoPaisValidator)
    }

    @PostMapping("/compras")
    fun fechaCompra(@RequestBody @Valid novaCompraRequest: NovaCompraRequest): String {
        return novaCompraRequest.toString()
    }
}