package br.com.samuca.lojalivros.validation

import jakarta.validation.Constraint
import kotlin.reflect.KClass

@Retention(AnnotationRetention.RUNTIME)
@Target(AnnotationTarget.FIELD)
@Constraint(validatedBy = [ExistsIdValidator::class])
annotation class ExistsId(val groups: Array<KClass<*>> = [], val payload: Array<KClass<*>> = [],
                          val domainClass: KClass<*>, val field: String, val message: String = "Id deve existir")