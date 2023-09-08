package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovoAutorRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class AutorRestController {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/autores")
    @Transactional
    fun save(@RequestBody @Valid autorRequest: NovoAutorRequest): String {
        val autor = autorRequest.toModel()
        entityManager.persist(autor)

        return autor.toString()
    }
}