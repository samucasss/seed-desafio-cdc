package br.com.samuca.lojalivros.validation

import jakarta.validation.ConstraintValidator
import jakarta.validation.ConstraintValidatorContext
import org.hibernate.validator.internal.constraintvalidators.hv.br.CNPJValidator
import org.hibernate.validator.internal.constraintvalidators.hv.br.CPFValidator

class DocumentoCpfCnpjValidator : ConstraintValidator<DocumentoCpfCnpj, Any> {

    override fun isValid(value: Any?, context: ConstraintValidatorContext): Boolean {
        val documento = value.toString()

        val cpfValidator = CPFValidator()
        cpfValidator.initialize(null)

        val cnpjValidator = CNPJValidator()
        cnpjValidator.initialize(null)

        return cpfValidator.isValid(documento, null) || cnpjValidator.isValid(documento, null)

        return false
    }
}