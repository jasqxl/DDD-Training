package com.thoughtworks.ddd.bootcamp.domain.product

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Nested
import org.junit.jupiter.api.Test
import java.util.Currency

internal class ProductTest {
    @Nested
    inner class PriceTest {
        @Test
        fun `should create price with default currency == USD`() {
            val price = Price(10.0)
            assertThat(price.currency.toString()).isEqualTo("USD")
        }

        @Test
        fun `should create price with given currency`() {
            val price = Price(10.0, Currency.getInstance("SGD"))
            assertThat(price.currency.toString()).isEqualTo("SGD")
        }

        @Test
        fun `should create price with given value`() {
            val price = Price(10.0)
            assertThat(price.value).isEqualTo(10.0)
        }

        @Test
        fun `should print price with correct format`() {
            val price = Price(10.0)
            assertThat(price.toString()).isEqualTo("$10.00 USD")
        }
    }
}
