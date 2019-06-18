package com.yotalabs.hunterroar.data

/**
 * @author Alexander Khyzhun
 * Created on 18 June, 2019
 */
interface AudioDataReceivedListener {
    fun onAudioDataReceived(data: ShortArray)
}