package com.dailycoffee.utils

import com.fasterxml.uuid.Generators
import java.util.*

object IdGenerator {
    private val GENERATOR = Generators.timeBasedGenerator()
    fun createId(): UUID {
        return GENERATOR.generate()
    }
}
