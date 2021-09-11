package com.thoughtworks.ddd.bootcamp.domain.product

class ProductPricingService {
    fun getProduct(productType: ProductType): Product {
        val competitorPriceValue = competitorProductPriceMap[productType]!!
        val discountedPriceValue = competitorPriceValue.times(DISCOUNT_RATE)

        return Product(type = productType, price = Price(value = discountedPriceValue))
    }

    companion object {
        private const val DISCOUNT_RATE = 0.9
    }
}
