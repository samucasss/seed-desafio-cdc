package br.com.samuca.lojalivros.response

data class DetalheCompraResponse(

    val cliente: DetalheClienteResponse,
    val pedido: List<DetalheItemPedidoResponse>,
    val codigoCupomDesconto: String?,
    val totalOriginalCompra: Double,
    val existeCupom: Boolean = false,
    val totalCompra: Double? = null
)