package com.cpsc411a.android.mobiledevpracticeproject.motivation

import android.os.Bundle
import android.util.Log
import android.widget.MediaController
import android.widget.VideoView
import androidx.appcompat.app.AppCompatActivity
import com.cpsc411a.android.mobiledevpracticeproject.R


private const val TAG = "MotivationActivity"

class MotivationActivity : AppCompatActivity() {

    lateinit var mVideoView : VideoView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.d(TAG, "onCreate(Bundle?) called")
        setContentView(R.layout.activity_motivation)

        val motivationVideos = assets.list("motivation_videos")
        Log.d(TAG, "Available Videos:")
        assets.list("motivation_videos")!!.forEach {
            Log.d(TAG, "${it}")
        }
        val it = motivationVideos?.random()

        Log.d(TAG, "Going to play: content://$packageName.motivation/motivation_videos/${it}")

        mVideoView = findViewById<VideoView>(R.id.motivationVideoView)
        val mediaController = MediaController(this)
        mVideoView.setMediaController(mediaController)
        mVideoView.setVideoPath("content://$packageName.motivation/motivation_videos/${it}")
        mVideoView.start()
    }

    //log functions
    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart() called")
    }
    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume() called")
    }
    override fun onPause() {
        super.onPause()
        Log.d(TAG, "onPause() called")
    }
    override fun onStop() {
        super.onStop()
        Log.d(TAG, "onStop() called")
    }
    override fun onDestroy() {
        super.onDestroy()
        Log.d(TAG, "onDestroy() called")
    }
}