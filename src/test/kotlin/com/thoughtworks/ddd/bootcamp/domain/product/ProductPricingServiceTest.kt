package com.thoughtworks.ddd.bootcamp.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class ProductPricingServiceTest {
    @Test
    fun `should get gm cricket bat product with discounted prices`() {
        val productPricingService = ProductPricingService()

        val product = productPricingService.getProduct(ProductType.GM_CRICKET_BAT)
        assertThat(product.type).isEqualTo(ProductType.GM_CRICKET_BAT)
        assertThat(product.price.value).isEqualTo(20.0 * 0.9)
    }

    @Test
    fun `should get hero ink pen product with discounted prices`() {
        val productPricingService = ProductPricingService()

        val product = productPricingService.getProduct(ProductType.HERO_INK_PEN)
        assertThat(product.type).isEqualTo(ProductType.HERO_INK_PEN)
        assertThat(product.price.value).isEqualTo(13.65 * 0.9)
    }
}
