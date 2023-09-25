package br.com.samuca.lojalivros.controller

import br.com.samuca.lojalivros.model.Compra
import br.com.samuca.lojalivros.repository.CupomDescontoRepository
import br.com.samuca.lojalivros.request.NovaCompraRequest
import br.com.samuca.lojalivros.response.DetalheClienteResponse
import br.com.samuca.lojalivros.response.DetalheCompraResponse
import br.com.samuca.lojalivros.response.DetalheItemPedidoLivroResponse
import br.com.samuca.lojalivros.response.DetalheItemPedidoResponse
import br.com.samuca.lojalivros.validation.CupomDescontoValidator
import br.com.samuca.lojalivros.validation.EstadoPaisValidator
import br.com.samuca.lojalivros.validation.TotalPedidoValidator
import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.WebDataBinder
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.InitBinder
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class CompraRestController(
    private val estadoPaisValidator: EstadoPaisValidator,
    private val totalPedidoValidator: TotalPedidoValidator,
    private val cupomDescontoRepository: CupomDescontoRepository
    ) {

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    @InitBinder
    fun init(binder: WebDataBinder) {
        binder.addValidators(estadoPaisValidator)
        binder.addValidators(totalPedidoValidator)
        binder.addValidators(CupomDescontoValidator(cupomDescontoRepository))
    }

    @PostMapping("/compras")
    @Transactional
    fun fecha(@RequestBody @Valid novaCompraRequest: NovaCompraRequest): String {
        val compra = novaCompraRequest.toModel(entityManager, cupomDescontoRepository)

        entityManager.persist(compra)
        return compra.toString()
    }

    @GetMapping("/compras/{id}")
    fun detalhe(@PathVariable id: Long): ResponseEntity<Any> {

        //retorna todas as compras
        val compraList = entityManager.createQuery("select c from Compra c", Compra::class.java).resultList

        val compra = entityManager.find(Compra::class.java, id)

        if (compra == null) {
            return ResponseEntity.notFound().build()
        }

        val detalheClienteResponse = DetalheClienteResponse(compra.email, compra.nome, compra.sobrenome, compra.documento,
            compra.endereco, compra.complemento, compra.cidade, compra.pais.nome, compra.estado?.nome, compra.telefone, compra.cep)

        val detalheItemPedidoResponseList = compra.pedido.itens.map { item ->
            DetalheItemPedidoResponse(item.quantidade, item.preco,
                DetalheItemPedidoLivroResponse(item.livro.id, item.livro.titulo))
        }

        val detalheCompraResponse = DetalheCompraResponse(detalheClienteResponse, detalheItemPedidoResponseList,
            compra.cupomAplicado?.cupomDesconto?.codigo, compra.totalOriginalCompra(), compra.hasCupom(),
            compra.totalCompraComDescontoCupom())

        return ResponseEntity.ok(detalheCompraResponse)
    }
}