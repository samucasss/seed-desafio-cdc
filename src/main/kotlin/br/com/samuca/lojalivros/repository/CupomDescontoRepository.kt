package br.com.samuca.lojalivros.repository

import br.com.samuca.lojalivros.model.CupomDesconto
import org.springframework.stereotype.Repository

@Repository
interface CupomDescontoRepository: org.springframework.data.repository.Repository<CupomDesconto, Long> {
    fun findByCodigo(codigo: String): CupomDesconto?

}
