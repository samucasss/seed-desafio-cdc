package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Categoria
import jakarta.validation.constraints.NotBlank

data class NovaCategoriaRequest(

    @field:NotBlank
    val nome: String

) {
    fun toModel(): Categoria {
        return Categoria(nome)
    }
}
