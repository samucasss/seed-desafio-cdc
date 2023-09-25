package br.com.samuca.lojalivros.validation

import br.com.samuca.lojalivros.repository.CupomDescontoRepository
import br.com.samuca.lojalivros.request.NovaCompraRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class CupomDescontoValidator(private val cupomDescontoRepository: CupomDescontoRepository): Validator {

    override fun supports(clazz: Class<*>): Boolean {
        return NovaCompraRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            return
        }

        val novaCompraRequest = target as NovaCompraRequest

        if (novaCompraRequest.temCupomDesconto()) {
            val cupomDesconto = cupomDescontoRepository.findByCodigo(novaCompraRequest.codigoCupom!!)

            if (cupomDesconto != null && !cupomDesconto.isValido()) {
                errors.rejectValue("codigoCupom", "novaCompraRequest.codigoCupom.rejectValue.invalido",
                    "Este cupom de desconto não é válido")
            }
        }
    }

}
