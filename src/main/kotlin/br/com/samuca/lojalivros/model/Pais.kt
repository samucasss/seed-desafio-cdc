package br.com.samuca.lojalivros.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.NotBlank

@Entity
class Pais(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank
    val nome: String

) {

    constructor(nome: String): this(0, nome)

    override fun toString(): String {
        return "Pais(id=$id, nome='$nome')"
    }
}