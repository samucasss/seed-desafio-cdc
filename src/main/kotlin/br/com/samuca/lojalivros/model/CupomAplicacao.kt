package br.com.samuca.lojalivros.model

import jakarta.persistence.Embeddable
import jakarta.persistence.Entity
import jakarta.persistence.ManyToOne
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.Positive
import java.time.LocalDate

@Embeddable
class CupomAplicacao(

    @ManyToOne
    val cupomDesconto: CupomDesconto,

    @field:Positive
    val percentual: Double,

    @field:Future
    val validade: LocalDate

) {
    constructor(cupomDesconto: CupomDesconto) : this(cupomDesconto, cupomDesconto.percentual, cupomDesconto.validade)

    override fun toString(): String {
        return "CupomAplicacao(cupomDesconto=$cupomDesconto, percentual=$percentual, validade=$validade)"
    }
}