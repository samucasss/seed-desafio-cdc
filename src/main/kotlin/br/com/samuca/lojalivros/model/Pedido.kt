package br.com.samuca.lojalivros.model

import jakarta.persistence.ElementCollection
import jakarta.persistence.Embeddable
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToMany
import jakarta.persistence.OneToOne
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

@Entity
class Pedido(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @OneToOne
    @field:NotNull
    @field:Valid
    val compra: Compra,

    @field:Size(min = 1)
    @ElementCollection
    val itens: Set<ItemPedido> = setOf()
) {

    constructor(compra: Compra, itensPedido: Set<ItemPedido>) : this(0, compra, itensPedido)

    override fun toString(): String {
        return "Pedido(id=$id, itensPedido=$itens)"
    }

    fun total(): Double {
        return itens.map { item -> item.total() }.sum()
    }

}