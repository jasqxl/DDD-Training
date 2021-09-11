package com.thoughtworks.ddd.bootcamp.domain

import java.util.UUID

open class Entity {
    protected val id: String = UUID.randomUUID().toString()

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Entity) {
            return this.id == other.id
        }
        return false
    }
}
