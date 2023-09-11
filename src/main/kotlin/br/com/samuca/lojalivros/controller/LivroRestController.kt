package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovoLivroRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class LivroRestController {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/livros")
    @Transactional
    fun save(@RequestBody @Valid novoLivroRequest: NovoLivroRequest): String {
        val livro = novoLivroRequest.toModel(entityManager)
        entityManager.persist(livro)

        return livro.toString()
    }

}