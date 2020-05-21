package com.pinelist.listpolicy.ports.utils

import com.pinelist.listpolicy.models.Item
import com.pinelist.listpolicy.models.Pinelist
import kotlin.random.Random

fun buildPinelist(
        id: String = "some-list-id-" + Random.nextInt(),
        name: String = "some-list-name-" + Random.nextInt(),
        items: List<Item> = listOf(buildItem())
): Pinelist {
    return Pinelist(
            id = id,
            name = name,
            items = items
    )
}

fun buildItem(
        id: String = "some-item-id-" + Random.nextInt(),
        name: String = "some-item-name-" + Random.nextInt()
): Item {
    return Item(
            id = id,
            name = name
    )
}