package com.neouul.umc10android.week10.data.data_source.local

import com.neouul.umc10android.week10.domain.model.Product

object DummyDataSource {
    val dummyWishProducts = listOf(
        Product(
            id = 1,
            name = "Nike Everyday Plus Cushioned",
            description = "Training Ankle Socks (6 Pairs)",
            colorNumber = 5,
            price = "US$10",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/76a44558-71e8-466d-837b-94c64379766e/EVERYDAY+PLUS+CUSH+ANKLE+6PR.png"
        ),
        Product(
            id = 2,
            name = "Nike Air Max Pulse",
            description = "Men's Shoes",
            colorNumber = 2,
            price = "US$150",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/60340316-291b-410c-967b-f4728564177d/AIR+MAX+PULSE.png"
        ),
        Product(
            id = 3,
            name = "Nike Sportswear Tech Fleece",
            description = "Men's Full-Zip Hoodie",
            colorNumber = 4,
            price = "US$130",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/6797a70a-4a6c-4828-9842-83677b47f4f6/AS+M+NSW+TCH+FLC+WR+HD+FZIP.png"
        ),
        Product(
            id = 4,
            name = "Nike Dri-FIT Challenger",
            description = "Men's 7\" Brief-Lined Shorts",
            colorNumber = 6,
            price = "US$45",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/09d845e0-165f-42e5-900f-48e02d4f23e0/AS+M+NK+DF+CHLLGR+7IN+SHORT.png"
        ),
        Product(
            id = 5,
            name = "Nike Air Force 1 '07",
            description = "Men's Shoes",
            colorNumber = 1,
            price = "US$110",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/b7d9211c-26e7-431a-ac24-b0540fb3c00f/AIR+FORCE+1+%2707.png"
        ),
        Product(
            id = 6,
            name = "Nike Sportswear Club Fleece",
            description = "Men's Joggers",
            colorNumber = 8,
            price = "US$60",
            img = "https://static.nike.com/a/images/t_PDP_1728_v1/f_auto,q_auto:eco/fcf2999e-761b-43f1-b461-1250328212e8/AS+M+NSW+CLUB+JGGR+BB.png"
        )
    )
}
