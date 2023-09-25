package br.com.samuca.lojalivros.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.Positive
import java.time.LocalDate

@Entity
class CupomDesconto(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank
    val codigo: String,

    @field:Positive
    val percentual: Double,

    @field:Future
    val validade: LocalDate

) {
    constructor(codigo: String, percentual: Double, validade: LocalDate) : this(0, codigo, percentual, validade)

    override fun toString(): String {
        return "CupomDesconto(id=$id, codigo='$codigo', percentual=$percentual, validade=$validade)"
    }

    fun isValido(): Boolean {
        return LocalDate.now().compareTo(validade) <= 0
    }
}