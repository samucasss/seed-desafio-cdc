package br.com.samuca.lojalivros.model

import jakarta.persistence.Embeddable
import jakarta.persistence.ManyToOne
import jakarta.validation.Valid
import jakarta.validation.constraints.Positive

@Embeddable
class ItemPedido(

    @field:Positive
    val quantidade: Int,

    @field:Positive
    val preco: Double,

    @ManyToOne
    @field:Valid
    val livro: Livro,

) {

    override fun toString(): String {
        return "ItemPedido(quantidade=$quantidade, preco=$preco, livro=$livro)"
    }

    override fun equals(other: Any?): Boolean {
        if (other == null || other !is ItemPedido) {
            return false
        }

        return livro.equals(other.livro)
    }

    override fun hashCode(): Int {
        return livro.hashCode()
    }

    fun total(): Double {
        return quantidade * preco
    }
}