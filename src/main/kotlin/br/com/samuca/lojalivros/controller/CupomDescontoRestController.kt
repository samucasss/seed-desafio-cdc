package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovoCupomDescontoRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CupomDescontoRestController {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/cupons")
    @Transactional
    fun save(@RequestBody @Valid novoCupomDescontoRequest: NovoCupomDescontoRequest): String {
        val cupomDesconto = novoCupomDescontoRequest.toModel()

        entityManager.persist(cupomDesconto)
        return cupomDesconto.toString()
    }
}