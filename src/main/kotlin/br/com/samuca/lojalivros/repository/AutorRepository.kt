package br.com.samuca.lojalivros.repository

import br.com.samuca.lojalivros.model.Autor
import org.springframework.data.repository.CrudRepository

interface AutorRepository: CrudRepository<Autor, Long> {
    fun existsByEmail(email: String): Boolean
}