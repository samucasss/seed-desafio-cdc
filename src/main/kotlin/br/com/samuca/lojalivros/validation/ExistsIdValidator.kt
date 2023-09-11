package br.com.samuca.lojalivros.validation

import jakarta.persistence.EntityManager
import jakarta.persistence.PersistenceContext
import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import kotlin.reflect.KClass

class ExistsIdValidator : ConstraintValidator<ExistsId, Any> {
    private lateinit var domainClass: KClass<*>
    private lateinit var field: String

    @PersistenceContext
    private lateinit var entityManager: EntityManager

    override fun initialize(constraintAnnotation: ExistsId) {
        domainClass = constraintAnnotation.domainClass
        field = constraintAnnotation.field
    }

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        val query = entityManager.createQuery("select 1 from ${domainClass.simpleName} where $field = :value")
        query.setParameter("value", value)

        return query.resultList.size > 0
    }
}