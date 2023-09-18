package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovoEstadoRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class EstadoRestController {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/estados")
    @Transactional
    fun save(@RequestBody @Valid novoEstadoRequest: NovoEstadoRequest): String {
        val estado = novoEstadoRequest.toModel(entityManager)
        entityManager.persist(estado)

        return estado.toString()
    }
}