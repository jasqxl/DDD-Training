package com.thoughtworks.ddd.bootcamp.domain.cart

import com.thoughtworks.ddd.bootcamp.domain.product.Price
import com.thoughtworks.ddd.bootcamp.domain.product.Product
import com.thoughtworks.ddd.bootcamp.domain.product.ProductType
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class CartTest {
    private val iPadPro = Product(type = ProductType.IPAD_PRO, price = Price(1100.1))
    private val heroInkPen = Product(type = ProductType.HERO_INK_PEN, price = Price(13.65))
    private val gmCricketBat = Product(type = ProductType.GM_CRICKET_BAT, price = Price(20.0))

    @Test
    fun `should initialize a cart with no products and status as PENDING`() {
        val cart = Cart()
        assertThat(cart.getItems()).isEmpty()
        assertThat(cart.getStatus()).isEqualTo(CartStatus.PENDING)
    }

    @Test
    fun `should update cart status from PENDING to CHECKED_OUT and return a list of products`() {
        val cart = Cart()

        cart.addProductsWithDiscountedPrice(ProductType.HERO_INK_PEN, 2)
        cart.addProductsWithDiscountedPrice(ProductType.IPAD_PRO, 1)

        assertThat(cart.getStatus()).isEqualTo(CartStatus.PENDING)

        val productsCheckedOut = cart.checkOut()

        assertThat(cart.getStatus()).isEqualTo(CartStatus.CHECKED_OUT)
        assertThat(productsCheckedOut.products).containsOnly(
            Product(type = ProductType.IPAD_PRO, price = Price(1100.1 * 0.9)),
            Product(type = ProductType.HERO_INK_PEN, price = Price(13.65 * 0.9)),
            Product(type = ProductType.HERO_INK_PEN, price = Price(13.65 * 0.9))
        )
    }

    @Test
    fun `should add ipad pro with discounted pricing`() {
        val cart = Cart()

        cart.addProductsWithDiscountedPrice(ProductType.IPAD_PRO, 1)

        val product = cart.getItems().first().product

        assertThat(product.price.value).isEqualTo(1100.1 * 0.9)
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

    @Test
    fun `should differentiate 1 cart from another cart`() {
        val cart1 = Cart()
        val cart2 = Cart()

        cart1.addProducts(heroInkPen, 1)
        cart2.addProducts(heroInkPen, 1)

        assertThat(cart1).usingRecursiveComparison().isNotEqualTo(cart2)
    }
}
