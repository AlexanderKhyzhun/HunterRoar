package com.yotalabs.hunterroar.data

/**
 * @author Alexander Khyzhun
 * Created on 18 June, 2019
 */
interface PlaybackListener {
    fun onProgress(progress: Int)

    fun onCompletion()
}
