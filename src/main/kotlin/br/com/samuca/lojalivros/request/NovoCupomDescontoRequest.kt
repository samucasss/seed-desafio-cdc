package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.CupomDesconto
import br.com.samuca.lojalivros.validation.UniqueValue
import com.fasterxml.jackson.annotation.JsonFormat
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.time.LocalDate

data class NovoCupomDescontoRequest(

    @field: NotBlank
    @UniqueValue(domainClass = CupomDesconto::class, field = "codigo")
    val codigo: String?,

    @field:NotNull
    @field:Positive
    val percentual: Double?,

    @field:Future
    @JsonFormat(pattern = "dd/MM/yyyy", shape = JsonFormat.Shape.STRING)
    val validade: LocalDate?

) {
    fun toModel(): CupomDesconto {
        return CupomDesconto(
            codigo = codigo!!,
            percentual = percentual!!,
            validade = validade!!
        )
    }

}