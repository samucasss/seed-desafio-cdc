package br.com.samuca.lojalivros.response

data class DetalheClienteResponse(

    val email: String,
    val nome: String,
    val sobrenome: String,
    val documento: String,
    val endereco: String,
    val complemento: String,
    val cidade: String,
    val pais: String,
    val estado: String?,
    val telefone: String,
    val cep: String,

)
