package br.com.samuca.lojalivros.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [DocumentoCpfCnpjValidator::class])
annotation class DocumentoCpfCnpj(val groups: Array<KClass<*>> = [], val payload: Array<KClass<*>> = [],
                                  val message: String = "Documento deve ser um CPF/CNPJ válido")