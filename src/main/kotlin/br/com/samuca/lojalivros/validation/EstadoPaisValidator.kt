package br.com.samuca.lojalivros.validation

import br.com.samuca.lojalivros.model.Estado
import br.com.samuca.lojalivros.model.Pais
import br.com.samuca.lojalivros.request.NovaCompraRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class EstadoPaisValidator: Validator {

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

        val pais = entityManager.find(Pais::class.java, novaCompraRequest.paisId)
        requireNotNull(pais) { "País não pode ser null" }

        if (pais.hasEstado() && novaCompraRequest.estadoId == null) {
            errors.rejectValue("estadoId", "NotNull", arrayOf("estadoId"),
                "O campo estado deve ser preenchido")
        }

        if (!pais.hasEstado() && novaCompraRequest.estadoId != null) {
            errors.rejectValue("estadoId", "novaCompraRequest.estadoId.rejectValue.estadosPaisEmpty",
                "Este país não possui estados")
        }

        if (pais.hasEstado() && novaCompraRequest.estadoId != null) {
            val estado = entityManager.find(Estado::class.java, novaCompraRequest.estadoId)

            requireNotNull(estado) { "Estado não pode ser null" }

            if (!pais.containsEstado(estado)) {
                errors.rejectValue("estadoId", "novaCompraRequest.estadoId.rejectValue.estadoNaoPertencePais",
                    "Este estado não é do país selecionado")
            }
        }
    }
}