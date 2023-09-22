package br.com.samuca.lojalivros.request

import br.com.samuca.lojalivros.model.Compra
import br.com.samuca.lojalivros.model.Estado
import br.com.samuca.lojalivros.model.Pais
import br.com.samuca.lojalivros.validation.DocumentoCpfCnpj
import br.com.samuca.lojalivros.validation.ExistsId
import jakarta.persistence.EntityManager
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

data class NovaCompraRequest(

    @field:NotBlank
    @field:Email
    val email: String?,

    @field:NotBlank
    val nome: String?,

    @field:NotBlank
    val sobrenome: String?,

    @field:NotBlank
    @DocumentoCpfCnpj
    val documento: String?,

    @field:NotBlank
    val endereco: String?,

    @field:NotBlank
    val complemento: String?,

    @field:NotBlank
    val cidade: String?,

    @field:NotNull
    @ExistsId(domainClass = Pais::class, field = "id")
    val paisId: Long?,

    @ExistsId(domainClass = Estado::class, field = "id")
    val estadoId: Long?,

    @field:NotBlank
    val telefone: String?,

    @field:NotBlank
    val cep: String?,

    @field:Valid
    @field:NotNull
    val pedido: NovoPedidoRequest?

) {

    fun isPedidoValid(): Boolean {
        return pedido != null && pedido.isValid()
    }

    override fun toString(): String {
        return "NovaCompraRequest(email=$email, nome=$nome, sobrenome=$sobrenome, documento=$documento, endereco=$endereco, " +
                "complemento=$complemento, cidade=$cidade, paisId=$paisId, estadoId=$estadoId, telefone=$telefone, cep=$cep)"
    }

    fun toModel(entityManager: EntityManager): Compra {
        val pais = entityManager.find(Pais::class.java, paisId)

        requireNotNull(pais) {"O país com id $paisId não foi encontrado"}
        val compra = Compra(email!!, nome!!, sobrenome!!, documento!!, endereco!!, complemento!!, cidade!!, pais, telefone!!,
            cep!!, pedido!!.toModel(entityManager))

        return compra
    }
}

