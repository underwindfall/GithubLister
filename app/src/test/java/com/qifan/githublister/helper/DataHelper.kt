package com.qifan.githublister.helper

import kotlin.random.Random

/**
 * Created by Qifan on 2019-08-15.
 */
fun randomUuid(): String {
    return java.util.UUID.randomUUID().toString()
}

fun randomInt(): Int {
    return Random.nextInt(0, 1000)
}

fun randomLong(): Long {
    return Random.nextLong()
}

fun randomBoolean(): Boolean {
    return Random.nextBoolean()
}

fun randomString(): String {
    return Random.nextFloat().toString()
}

fun makeStringList(count: Int): List<String> {
    val items: MutableList<String> = mutableListOf()
    repeat(count) {
        items.add(randomUuid())
    }
    return items
}