package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Autor
import br.com.samuca.lojalivros.validation.UniqueValue
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Size

data class NovoAutorRequest(

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    @UniqueValue(domainClass = Autor::class, field = "email", message = "{Unique.novoAutorRequest.email}")
    val email: String,

    @field:NotBlank
    @field:Size(max = 400)
    val descricao: String,

) {

    fun toModel(): Autor {
        return Autor(nome, email, descricao)
    }
}