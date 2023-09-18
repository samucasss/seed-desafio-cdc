package br.com.samuca.lojalivros.response

import br.com.samuca.lojalivros.model.Livro
import java.time.format.DateTimeFormatter

data class DetalheLivroResponse(val titulo: String, val resumo: String, val sumario: String?, val preco: Double,
                                val numeroPaginas: Int, val isbn: String, val dataPublicacao: String, val autor: DetalheAutorResponse) {


    constructor(livro: Livro): this(livro.titulo, livro.resumo, livro.sumario, livro.preco, livro.numeroPaginas,
        livro.isbn, livro.dataPublicacao.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),
        DetalheAutorResponse(livro.autor.nome, livro.autor.descricao))
}