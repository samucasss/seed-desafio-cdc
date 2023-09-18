package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Pais
import br.com.samuca.lojalivros.validation.UniqueValue
import jakarta.validation.constraints.NotBlank

data class NovoPaisRequest(

    @field:NotBlank
    @UniqueValue(domainClass = Pais::class, field = "nome")
    val nome: String?

) {
    fun toModel(): Pais {
        return Pais(nome = nome!!)
    }
}
