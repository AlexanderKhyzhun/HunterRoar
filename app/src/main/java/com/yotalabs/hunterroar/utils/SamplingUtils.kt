package com.yotalabs.hunterroar.utils

import java.util.*


fun getExtremes(data: ShortArray, sampleSize: Int): Array<ShortArray?> {
    val newData = arrayOfNulls<ShortArray>(sampleSize)
    val groupSize = data.size / sampleSize

    for (i in 0 until sampleSize) {
        val group = Arrays.copyOfRange(
            data, i * groupSize,
            Math.min((i + 1) * groupSize, data.size)
        )

        // Fin min & max values
        var min = java.lang.Short.MAX_VALUE
        var max = java.lang.Short.MIN_VALUE
        for (a in group) {
            min = Math.min(min.toInt(), a.toInt()).toShort()
            max = Math.max(max.toInt(), a.toInt()).toShort()
        }
        newData[i] = shortArrayOf(max, min)
    }
    return newData
}

