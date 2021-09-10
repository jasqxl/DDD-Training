package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.product.Product

class Cart {
    private val products = hashSetOf<Product>()

    fun addProduct(product: Product) {
        products.add(product)
    }

    fun getProducts(): List<Product> = products.toList()
}
