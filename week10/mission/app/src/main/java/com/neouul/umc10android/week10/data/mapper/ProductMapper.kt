package com.neouul.umc10android.week10.data.mapper

import com.neouul.umc10android.week10.data.data_source.local.entity.ProductWithWish
import com.neouul.umc10android.week10.data.data_source.local.entity.ProductEntity
import com.neouul.umc10android.week10.domain.model.Product

fun ProductWithWish.toDomain(): Product {
    return Product(
        id = id,
        name = name,
        description = description,
        detailDescription = detailDescription,
        category = category,
        colorNumber = colorNumber,
        price = price,
        img = img,
        isBestSeller = isBestSeller,
        isWished = isWished
    )
}

fun Product.toEntity(): ProductEntity {
    return ProductEntity(
        id = id,
        name = name,
        description = description,
        detailDescription = detailDescription,
        category = category,
        colorNumber = colorNumber,
        price = price,
        img = img,
        isBestSeller = isBestSeller
    )
}
