package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.Entity
import com.thoughtworks.ddd.bootcamp.domain.ValueObject
import com.thoughtworks.ddd.bootcamp.domain.product.Product
import com.thoughtworks.ddd.bootcamp.domain.product.ProductPricingService
import com.thoughtworks.ddd.bootcamp.domain.product.ProductType

enum class CartStatus {
    CHECKED_OUT, PENDING
}

data class Order(val products: List<Product>) : ValueObject

class Cart : Entity() {
    private var status: CartStatus = CartStatus.PENDING

    private val productQuantityMapping: HashMap<Product, Int> = hashMapOf()
    private val deletedProductQuantityMap: HashMap<Product, Int> = hashMapOf()

    private val productPricingService = ProductPricingService()

    fun addProducts(product: Product, quantity: Int) {
//        productQuantityMapping[product] = Optional.ofNullable(productQuantityMapping[product])
//            .map { currentQuantity -> currentQuantity + quantity }
//            .orElse(quantity)
        productQuantityMapping[product] =
            productQuantityMapping[product]?.let { it + quantity } ?: quantity
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

    fun addProductsWithDiscountedPrice(productType: ProductType, quantity: Int) {
        this.addProducts(productPricingService.getProduct(productType), quantity)
    }

    fun getStatus(): CartStatus = this.status

    fun checkOut(): Order {
        status = CartStatus.CHECKED_OUT
        return Order(getItems().map { e -> IntRange(0, e.quantity).map { e.product } }.flatten())
    }
}

data class Item(val product: Product, val quantity: Int) : ValueObject
