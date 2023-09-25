package br.com.samuca.lojalivros.model

import br.com.samuca.lojalivros.validation.DocumentoCpfCnpj
import jakarta.persistence.CascadeType
import jakarta.persistence.Embedded
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.ManyToOne
import jakarta.persistence.OneToOne
import jakarta.validation.Valid
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import java.util.function.Function

@Entity
class Compra(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    val sobrenome: String,

    @field:NotBlank
    @DocumentoCpfCnpj
    val documento: String,

    @field:NotBlank
    val endereco: String,

    @field:NotBlank
    val complemento: String,

    @field:NotBlank
    val cidade: String,

    @ManyToOne
    val pais: Pais,

    @ManyToOne
    var estado: Estado?,

    @field:NotBlank
    val telefone: String,

    @field:NotBlank
    val cep: String,

    @Embedded
    var cupomAplicado: CupomAplicacao?
) {


    @OneToOne(mappedBy = "compra", cascade = [CascadeType.PERSIST])
    @field:NotNull
    @field:Valid
    lateinit var pedido: Pedido

    constructor(
        email: String,
        nome: String,
        sobrenome: String,
        documento: String,
        endereco: String,
        complemento: String,
        cidade: String,
        pais: Pais,
        telefone: String,
        cep: String,
        funcaoCriacaoPedido: Function<Compra, Pedido>
    ) : this(
        0,
        email,
        nome,
        sobrenome,
        documento,
        endereco,
        complemento,
        cidade,
        pais,
        null,
        telefone,
        cep,
        null
    )
    {
        this.pedido = funcaoCriacaoPedido.apply(this)
    }

    override fun toString(): String {
        return "Compra(id=$id, email=$email, nome=$nome, sobrenome=$sobrenome, documento=$documento, endereco=$endereco, " +
                "complemento=$complemento, cidade=$cidade, pais=$pais, estado=$estado, telefone=$telefone, cep=$cep, " +
                "pedido=$pedido), cupomAplicado=$cupomAplicado"
    }

    fun aplicarCupom(cupomDesconto: CupomDesconto) {
        require(cupomDesconto.isValido()) {"O cupom deve ser valido"}
        require(this.cupomAplicado == null) {"Já existe um cupom aplicado a essa compra"}

        this.cupomAplicado = CupomAplicacao(cupomDesconto)
    }

    fun totalOriginalCompra(): Double {
        return pedido.total()
    }

    fun hasCupom(): Boolean {
        return cupomAplicado != null
    }

    fun totalCompraComDescontoCupom(): Double? {
        if (hasCupom()) {
            return totalOriginalCompra() * (1 - cupomAplicado!!.cupomDesconto!!.percentual / 100)
        }

        return null
    }

}