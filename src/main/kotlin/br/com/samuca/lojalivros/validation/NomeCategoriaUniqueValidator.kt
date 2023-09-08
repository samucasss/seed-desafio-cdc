package br.com.samuca.lojalivros.validation

import br.com.samuca.lojalivros.repository.CategoriaRepository
import br.com.samuca.lojalivros.request.NovaCategoriaRequest
import org.springframework.stereotype.Component
import org.springframework.validation.Errors
import org.springframework.validation.Validator

@Component
class NomeCategoriaUniqueValidator(private val categoriaRepository: CategoriaRepository): Validator {
    override fun supports(clazz: Class<*>): Boolean {
        return NovaCategoriaRequest::class.java.isAssignableFrom(clazz)
    }

    override fun validate(target: Any, errors: Errors) {
        if (errors.hasErrors()) {
            return
        }

        val novaCategoriaRequest = target as NovaCategoriaRequest
        val existsByNome = categoriaRepository.existsByNomeIgnoreCase(novaCategoriaRequest.nome)
        if (existsByNome) {
            errors.rejectValue("nome", "Unique.novaCategoriaRequest.nome", arrayOf(novaCategoriaRequest.nome),
                "Já existe uma outra categoria com o mesmo nome ${novaCategoriaRequest.nome}")
        }
    }
}