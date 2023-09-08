package br.com.samuca.lojalivros.validation

import br.com.samuca.lojalivros.repository.AutorRepository
import br.com.samuca.lojalivros.request.NovoAutorRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class EmailAutorUniqueValidator(private val autorRepository: AutorRepository): Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return NovoAutorRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            return
        }

        val novoAutorRequest = target as NovoAutorRequest
        val existsByEmail = autorRepository.existsByEmail(novoAutorRequest.email)
        if (existsByEmail) {
            errors.rejectValue("email", "Unique.novoAutorRequest.email", arrayOf(novoAutorRequest.email),
                "Já existe um(a) outro(a) autor(a) com o mesmo email ${novoAutorRequest.email}")
        }
    }
}