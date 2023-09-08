package br.com.samuca.lojalivros.repository

import br.com.samuca.lojalivros.model.Categoria
import org.springframework.data.repository.CrudRepository

interface CategoriaRepository: CrudRepository<Categoria, Long> {

    fun existsByNomeIgnoreCase(nome: String): Boolean
}