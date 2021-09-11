package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.product.Product
import java.util.UUID

class Cart {
    private val id: String = UUID.randomUUID().toString()

    private val productQuantityMapping: HashMap<Product, Int> = hashMapOf()
    private val deletedProductQuantityMap: HashMap<Product, Int> = hashMapOf()

    fun addProducts(product: Product, quantity: Int) {
        val currentQuantity = productQuantityMapping[product]

        if (currentQuantity == null) {
            productQuantityMapping[product] = quantity

        } else {
            productQuantityMapping[product] = currentQuantity + quantity
        }
    }

    fun removeProducts(product: Product, quantity: Int) {
        val currentQuantity = productQuantityMapping[product] ?: return

        if (currentQuantity - quantity <= 0) {
            productQuantityMapping.remove(product)
            deletedProductQuantityMap[product] = currentQuantity
        } else {
            productQuantityMapping[product] = currentQuantity - quantity
            deletedProductQuantityMap[product] = quantity
        }
    }

    fun getItems(): List<Item> = productQuantityMapping.getItemsFromMap()

    fun getRemovedItems(): List<Item> = deletedProductQuantityMap.getItemsFromMap()

    private fun HashMap<Product, Int>.getItemsFromMap(): List<Item> =
        this.entries.map { e -> Item(e.key, e.value) }

    override fun equals(other: Any?): Boolean {
        if (other != null && other is Cart) {
            return this.id == other.id
        }
        return false
    }

    override fun hashCode(): Int {
        var result = id.hashCode()
        result = 31 * result + productQuantityMapping.hashCode()
        result = 31 * result + deletedProductQuantityMap.hashCode()
        return result
    }
}
