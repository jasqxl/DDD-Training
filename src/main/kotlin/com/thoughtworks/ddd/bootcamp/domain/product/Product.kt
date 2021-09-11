package com.thoughtworks.ddd.bootcamp.domain.product

import java.util.Currency

data class Product(val type: ProductType, val price: Price)

data class Price(val value: Double, val currency: Currency = Currency.getInstance("USD")) {
    override fun toString(): String = "\$${String.format("%.2f", value)} $currency"
}

enum class ProductType {
    IPAD_PRO, HERO_INK_PEN, GM_CRICKET_BAT
}
