package com.thoughtworks.ddd.bootcamp.domain.product

import com.thoughtworks.ddd.bootcamp.domain.ValueObject
import java.util.Currency

data class Product(val type: ProductType, val price: Price) : ValueObject

data class Price(val value: Double, val currency: Currency = Currency.getInstance("USD")) :
    ValueObject {
    override fun toString(): String = "\$${String.format("%.2f", value)} $currency"
}

enum class ProductType {
    IPAD_PRO, HERO_INK_PEN, GM_CRICKET_BAT
}
