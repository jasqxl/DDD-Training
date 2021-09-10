package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.product.Product
import com.thoughtworks.ddd.bootcamp.domain.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CartTest {
    private val iPadPro = Product(ProductType.IPAD_PRO)
    private val heroInkPen = Product(ProductType.HERO_INK_PEN)
    private val gmCricketBat = Product(ProductType.GM_CRICKET_BAT)

    @Test
    fun `should initialize a cart with no products`() {
        val cart = Cart()
        assertThat(cart.getItems()).isEmpty()
    }

    @Test
    fun `should add ipad pro as a product to a cart`() {
        val cart = Cart()

        cart.addProducts(iPadPro, 1)
        assertThat(cart.getItems()).containsOnly(Item(iPadPro, 1))
    }

    @Test
    fun `should add hero ink pen as a product to a cart`() {
        val cart = Cart()

        cart.addProducts(heroInkPen, 1)
        assertThat(cart.getItems()).containsOnly(Item(heroInkPen, 1))
    }

    @Test
    fun `should add 2 GM Cricket bats as a product to a cart`() {
        val cart = Cart()

        cart.addProducts(gmCricketBat, 2)
        assertThat(cart.getItems()).hasSize(1)
        assertThat(cart.getItems()).containsOnly(Item(gmCricketBat, 2))
    }

    @Test
    fun `should remove 1 GM Cricket bat from a cart containing 2 GM Cricket bats`() {
        val cart = Cart()

        cart.addProducts(gmCricketBat, 2)
        cart.removeProducts(gmCricketBat, 1)
        assertThat(cart.getItems()).hasSize(1)
        assertThat(cart.getItems()).containsOnly(Item(gmCricketBat, 1))
    }

    @Test
    fun `should remove all GM Cricket bats from a cart containing 2 GM Cricket bats`() {
        val cart = Cart()

        cart.addProducts(gmCricketBat, 2)
        cart.removeProducts(gmCricketBat, 2)
        assertThat(cart.getItems()).isEmpty()
    }

    @Test
    fun `should return all deleted products from cart`() {
        val cart = Cart()

        cart.addProducts(gmCricketBat, 2)
        cart.addProducts(iPadPro, 2)
        cart.addProducts(heroInkPen, 3)

        cart.removeProducts(gmCricketBat, 2)
        cart.removeProducts(iPadPro, 1)

        assertThat(cart.getRemovedItems()).containsOnly(Item(gmCricketBat, 2), Item(iPadPro, 1))
    }
//    @Test
//    fun `should differentiate 1 cart from another cart`() {
//        val cart1 = Cart()
//        val cart2 = Cart()
//
//        cart1.addProducts(heroInkPen, 1)
//        cart2.addProducts(heroInkPen, 1)
//
//        assertThat(cart1).usingRecursiveComparison().isNotEqualTo(cart2)
//    }
}
