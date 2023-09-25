package br.com.samuca.lojalivros.response

data class DetalheItemPedidoResponse(

    val quantidade: Int,
    val preco: Double,
    val livro: DetalheItemPedidoLivroResponse
)