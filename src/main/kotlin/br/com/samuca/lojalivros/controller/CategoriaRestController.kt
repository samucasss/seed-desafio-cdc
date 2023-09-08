package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovaCategoriaRequest
import br.com.samuca.lojalivros.validation.NomeCategoriaUniqueValidator
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CategoriaRestController(private val nomeCategoriaUniqueValidator: NomeCategoriaUniqueValidator) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(nomeCategoriaUniqueValidator)
    }

    @PostMapping("/categorias")
    @Transactional
    fun save(@RequestBody @Valid novaCategoriaRequest: NovaCategoriaRequest): String {
        val categoria = novaCategoriaRequest.toModel()
        entityManager.persist(categoria)

        return categoria.toString()
    }
}