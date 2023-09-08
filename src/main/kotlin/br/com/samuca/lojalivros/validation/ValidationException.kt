package br.com.samuca.lojalivros.validation

class ValidationException(val errors: List<String>) : RuntimeException("Validation failed")
