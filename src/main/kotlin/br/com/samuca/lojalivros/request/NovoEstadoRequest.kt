package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Estado
import br.com.samuca.lojalivros.model.Pais
import br.com.samuca.lojalivros.validation.ExistsId
import br.com.samuca.lojalivros.validation.UniqueValue
import jakarta.persistence.EntityManager
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class NovoEstadoRequest(

    @field:NotBlank
    @UniqueValue(domainClass = Estado::class, field = "nome")
    val nome: String?,

    @field:NotNull
    @ExistsId(domainClass = Pais::class, field = "id")
    val paisId: Long?

) {

    fun toModel(entityManager: EntityManager): Estado {
        val pais = entityManager.find(Pais::class.java, paisId)

        return Estado(nome = nome!!, pais = pais)
    }
}
