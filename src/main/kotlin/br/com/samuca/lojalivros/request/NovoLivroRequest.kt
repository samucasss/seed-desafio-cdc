package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Autor
import br.com.samuca.lojalivros.model.Categoria
import br.com.samuca.lojalivros.model.Livro
import br.com.samuca.lojalivros.validation.ExistsId
import br.com.samuca.lojalivros.validation.UniqueValue
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.persistence.EntityManager
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size
import java.time.LocalDate

data class NovoLivroRequest(

    @field:NotBlank
    @UniqueValue(domainClass = Livro::class, field = "titulo", message = "{Unique.novoLivroRequest.titulo}")
    val titulo: String?,

    @field:NotBlank
    @field:Size(max = 500)
    val resumo: String?,

    val sumario: String?,

    @field:NotNull
    @field:Min(value = 20)
    val preco: Double?,

    @field:NotNull
    @field:Min(value = 100)
    val numeroPaginas: Int?,

    @field:NotBlank
    @UniqueValue(domainClass = Livro::class, field = "isbn", message = "{Unique.novoLivroRequest.isbn}")
    val isbn: String?,

    @field:Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val dataPublicacao: LocalDate?,

    @field:NotNull
    @ExistsId(domainClass = Categoria::class, field = "id", message = "{ExistsId.novoLivroRequest.categoriaId}")
    val categoriaId: Long?,

    @field:NotNull
    @ExistsId(domainClass = Autor::class, field = "id", message = "{ExistsId.novoLivroRequest.autorId}")
    val autorId: Long?

) {

    fun toModel(entityManager: EntityManager): Livro {
        val categoria = entityManager.find(Categoria::class.java, categoriaId)
        val autor = entityManager.find(Autor::class.java, autorId)

        assert(categoria != null) { "Categoria não existe no banco" }
        assert(autor != null) { "Autor não existe no banco" }

        return Livro(
            titulo = titulo!!,
            resumo = resumo!!,
            sumario = sumario,
            preco = preco!!,
            numeroPaginas = numeroPaginas!!,
            isbn = isbn!!,
            dataPublicacao = dataPublicacao!!,
            categoria = categoria,
            autor = autor
        )
    }
}
