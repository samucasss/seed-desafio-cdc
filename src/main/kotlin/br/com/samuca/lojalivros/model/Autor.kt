package br.com.samuca.lojalivros.model

import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank
import org.jetbrains.annotations.NotNull
import java.time.LocalDateTime

@Entity
class Autor(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    @field:NotBlank
    val nome: String,

    @field:NotBlank
    @field:Email
    val email: String,

    @field:NotBlank
    val descricao: String,

    @field:NotNull
    val dataHoraRegistro: LocalDateTime = LocalDateTime.now()

) {
    constructor(nome: String, email: String, descricao: String): this(0, nome, email, descricao, LocalDateTime.now())

    override fun toString(): String {
        return "Autor(id=$id, nome='$nome', email='$email', descricao='$descricao', dataHoraRegistro=$dataHoraRegistro)"
    }
}