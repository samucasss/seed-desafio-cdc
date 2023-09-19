package br.com.samuca.lojalivros.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid
import jakarta.validation.constraints.NotBlank

@Entity
data class Estado(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank
    val nome: String,

    @ManyToOne
    @Valid
    val pais: Pais

) {

    constructor(nome: String, pais: Pais): this(0, nome, pais)

    override fun toString(): String {
        return "Estado(id=$id, nome='$nome', pais=$pais)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is Estado) {
            return false
        }

        return nome.equals(other?.nome) && pais.equals(other?.pais)
    }

    override fun hashCode(): Int {
        return 31*nome.hashCode() + pais.hashCode()
    }
}
