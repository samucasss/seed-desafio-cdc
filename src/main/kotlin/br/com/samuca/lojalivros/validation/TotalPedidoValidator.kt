package br.com.samuca.lojalivros.validation

import br.com.samuca.lojalivros.request.NovaCompraRequest
import br.com.samuca.lojalivros.request.NovoPedidoRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class TotalPedidoValidator: Validator {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun supports(clazz: Class<*>): Boolean {
        return NovaCompraRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            return
        }

        val novaCompraRequest = target as NovaCompraRequest

        if (novaCompraRequest.isPedidoValid() && !novaCompraRequest.pedido!!.validaTotal(entityManager)) {
            errors.rejectValue("pedido", "novaCompraRequest.pedido.total.rejectValue",
                "O total do pedido é diferente do total calculado")
        }

    }
}