package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Compra
import br.com.samuca.lojalivros.model.ItemPedido
import br.com.samuca.lojalivros.model.Pedido
import jakarta.persistence.EntityManager
import jakarta.validation.Valid
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import jakarta.validation.constraints.Size
import java.util.function.Function

data class NovoPedidoRequest(

    @field:NotNull
    @field:Positive
    val total: Double?,

    @field:Valid
    @field:Size(min = 1)
    val itens: List<NovoItemPedidoRequest> = arrayListOf()

) {

    fun toModel(entityManager: EntityManager): Function<Compra, Pedido> {
        val itens: Set<ItemPedido> = this.itens!!.map { it.toModel(entityManager) }.toSet()

        val funcaoPedidoCompra = Function<Compra, Pedido> { compra ->
            Pedido(compra, itens)
        }

        return funcaoPedidoCompra
    }

    fun isValid(): Boolean {
        return total != null && total > 0 && !itens.isEmpty() && itens.all { it.isValid() }
    }

    fun validaTotal(entityManager: EntityManager): Boolean {
        val totalCalculado = itens!!.map { it.calculaTotal(entityManager) }.sum()
        return totalCalculado == total
    }
}
