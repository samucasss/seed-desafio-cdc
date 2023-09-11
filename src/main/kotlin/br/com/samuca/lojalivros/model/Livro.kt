package br.com.samuca.lojalivros.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid
import jakarta.validation.constraints.*
import java.time.LocalDate

@Entity
class Livro(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank
    val titulo: String,

    @field:NotBlank
    @field:Size(max = 500)
    val resumo: String,

    val sumario: String?,

    @field:NotNull
    @field:Min(value = 20)
    val preco: Double,

    @field:NotNull
    @field:Min(value = 100)
    val numeroPaginas: Int,

    @field:NotBlank
    val isbn: String,

    @field:Future
    val dataPublicacao: LocalDate,

    @ManyToOne
    @field:NotNull
    @Valid
    val categoria: Categoria,

    @ManyToOne
    @field:NotNull
    @Valid
    val autor: Autor

) {
    constructor(titulo: String, resumo: String, sumario: String?, preco: Double, numeroPaginas: Int, isbn: String,
                dataPublicacao: LocalDate, categoria: Categoria?, autor: Autor?) :
            this(0, titulo, resumo, sumario, preco, numeroPaginas, isbn, dataPublicacao, categoria!!, autor!!)

    override fun toString(): String {
        return "Livro(id=$id, titulo='$titulo', resumo='$resumo', sumario=$sumario, preco=$preco, numeroPaginas=$numeroPaginas, " +
                "isbn='$isbn', dataPublicacao=$dataPublicacao, categoria=$categoria, autor=$autor)"
    }
}