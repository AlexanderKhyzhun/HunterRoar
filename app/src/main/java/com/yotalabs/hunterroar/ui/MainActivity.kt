package com.yotalabs.hunterroar.ui

import android.Manifest.permission.RECORD_AUDIO
import android.content.pm.PackageManager
import android.content.pm.PackageManager.PERMISSION_GRANTED
import android.os.Bundle
import androidx.annotation.NonNull
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.google.android.material.snackbar.Snackbar
import com.yotalabs.hunterroar.R
import com.yotalabs.hunterroar.data.AudioDataReceivedListener
import com.yotalabs.hunterroar.data.RecordingThread
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    companion object {
        private const val REQUEST_RECORD_AUDIO = 13
    }

    private lateinit var mRecordingThread: RecordingThread

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(activity_main_toolbar)

        mRecordingThread = RecordingThread(object : AudioDataReceivedListener {
            override fun onAudioDataReceived(data: ShortArray) {
                waveform_view_recording.samples = data
            }
        })

        activity_main_fab_play.setOnClickListener {
            when (mRecordingThread.recording()) {
                true -> mRecordingThread.stopRecording()
                false -> startAudioRecordingSafe()
            }
        }
    }

    private fun startAudioRecordingSafe() {
        if (ContextCompat.checkSelfPermission(this, RECORD_AUDIO) != PERMISSION_GRANTED) {
            mRecordingThread.startRecording()
        } else {
            requestMicrophonePermission()
        }
    }

    private fun requestMicrophonePermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, RECORD_AUDIO)) {
            // Show dialog explaining why we need record audio
            Snackbar.make(
                waveform_view_playback,
                "Microphone access is required in order to record audio",
                Snackbar.LENGTH_INDEFINITE
            ).setAction(
                "OK"
            ) {
                ActivityCompat.requestPermissions(
                    this@MainActivity,
                    arrayOf(RECORD_AUDIO),
                    REQUEST_RECORD_AUDIO
                )
            }.show()
        } else {
            ActivityCompat.requestPermissions(
                this@MainActivity,
                arrayOf(RECORD_AUDIO),
                REQUEST_RECORD_AUDIO
            )
        }
    }


    override fun onRequestPermissionsResult(
        requestCode: Int,
        @NonNull permissions: Array<String>,
        @NonNull grantResults: IntArray
    ) {

        if (requestCode == REQUEST_RECORD_AUDIO && grantResults.isNotEmpty() &&
            grantResults[0] == PERMISSION_GRANTED
        ) {
            mRecordingThread.stopRecording()
        }
    }

}
