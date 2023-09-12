package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.model.Livro
import br.com.samuca.lojalivros.request.NovoLivroRequest
import br.com.samuca.lojalivros.response.LivroResponse
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.GetMapping
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

    @GetMapping("/livros")
    fun list(): List<LivroResponse> {
        val livroList = entityManager.createQuery("select l from Livro l", Livro::class.java).resultList
        val livroResponseList = livroList.map { livro -> LivroResponse(livro.id, livro.titulo) }

        return livroResponseList
    }

}