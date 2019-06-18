package com.yotalabs.hunterroar.utils

/**
 * @author Alexander Khyzhun
 * Created on 18 June, 2019
 */

fun findAudioLength(
    samplesCount: Int,
    sampleRate: Int,
    channelCount: Int
): Int = samplesCount / channelCount * 1000 / sampleRate
