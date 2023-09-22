package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.ItemPedido
import br.com.samuca.lojalivros.model.Livro
import br.com.samuca.lojalivros.validation.ExistsId
import jakarta.persistence.EntityManager
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive

data class NovoItemPedidoRequest(

    @field:NotNull
    @ExistsId(domainClass = Livro::class)
    val idLivro: Long?,

    @field:NotNull
    @field:Positive
    val quantidade: Int?

) {

    fun toModel(entityManager: EntityManager): ItemPedido {
        val livro = entityManager.find(Livro::class.java, idLivro)

        requireNotNull(livro) {"O livro com id $idLivro não foi encontrado"}
        requireNotNull(quantidade) {"Quantidade não pode ser null"}

        return ItemPedido(quantidade!!, livro.preco, livro)
    }

    fun isValid(): Boolean {
        return idLivro != null && idLivro > 0 && quantidade != null && quantidade > 0
    }

    fun calculaTotal(entityManager: EntityManager): Double {
        val livro = entityManager.find(Livro::class.java, idLivro)

        requireNotNull(livro) {"O livro com id $idLivro não foi encontrado"}
        requireNotNull(quantidade) {"Quantidade não pode ser null"}

        return quantidade!!*livro.preco
    }
}