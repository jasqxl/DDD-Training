package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.product.Product
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CartTest {
    @Test
    fun `should initialize a cart with no products`() {
        val cart = Cart()
        assertThat(cart.getProducts()).hasSize(0)
    }

    @Test
    fun `should add ipad pro as a product to a cart`() {
        val cart = Cart()
        val iPadPro = Product(1, "IPad Pro")

        cart.addProduct(iPadPro)
        assertThat(cart.getProducts()).containsOnly(iPadPro)
    }

    @Test
    fun `should add hero ink pen as a product to a cart`() {
        val cart = Cart()
        val heroInkPen = Product(2, "Hero ink Pen")

        cart.addProduct(heroInkPen)
        assertThat(cart.getProducts()).containsOnly(heroInkPen)
    }

    @Test
    fun `should add 2 GM Cricket bats as a product to a cart`() {
        val cart = Cart()
        val bat1 = Product(1, "GM Cricket bat")
        val bat2 = Product(2, "GM Cricket bat")

        cart.addProduct(bat1)
        cart.addProduct(bat2)
        assertThat(cart.getProducts()).hasSize(2)
        assertThat(cart.getProducts()).containsOnly(bat1, bat2)
    }
}
