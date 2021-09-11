package com.thoughtworks.ddd.bootcamp.domain.product

import java.util.Currency

data class Product(val type: ProductType, val price: Price)

data class Price(val value: Double, val currency: Currency = Currency.getInstance("USD"))
