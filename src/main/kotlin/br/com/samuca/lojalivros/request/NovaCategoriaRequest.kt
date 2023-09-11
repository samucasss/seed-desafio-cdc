package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Categoria
import br.com.samuca.lojalivros.validation.UniqueValue
import jakarta.validation.constraints.NotBlank

data class NovaCategoriaRequest(

    @field:NotBlank
    @UniqueValue(domainClass = Categoria::class, field = "nome", message = "{Unique.novaCategoriaRequest.nome}")
    val nome: String

) {
    fun toModel(): Categoria {
        return Categoria(nome)
    }
}
