package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.request.NovoPaisRequest
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class PaisRestController {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @PostMapping("/paises")
    @Transactional
    fun save(@RequestBody @Valid novoPaisRequest: NovoPaisRequest): String {
        val pais = novoPaisRequest.toModel()
        entityManager.persist(pais)

        return pais.toString()
    }
}