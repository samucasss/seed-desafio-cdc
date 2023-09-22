package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovaCompraRequest
import br.com.samuca.lojalivros.validation.EstadoPaisValidator
import br.com.samuca.lojalivros.validation.TotalPedidoValidator
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.validation.Valid
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompraRestController(
    private val estadoPaisValidator: EstadoPaisValidator,
    private val totalPedidoValidator: TotalPedidoValidator
    ) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(estadoPaisValidator)
        binder.addValidators(totalPedidoValidator)
    }

    @PostMapping("/compras")
    fun fechaCompra(@RequestBody @Valid novaCompraRequest: NovaCompraRequest): String {
        val compra = novaCompraRequest.toModel(entityManager)
        return compra.toString()
    }
}