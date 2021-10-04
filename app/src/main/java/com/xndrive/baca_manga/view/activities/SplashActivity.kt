package com.xndrive.baca_manga.view.activities

import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import com.xndrive.baca_manga.R
import com.xndrive.baca_manga.databinding.ActivitySplashBinding
import java.util.concurrent.Executors
import java.util.concurrent.TimeUnit

class SplashActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var _splashBinding: ActivitySplashBinding
    private lateinit var _animation1: Animation
    private lateinit var _animation2: Animation

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _splashBinding = ActivitySplashBinding.inflate(layoutInflater)
        setContentView(_splashBinding.root)

        //fullscreen
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R){
            window.insetsController?.hide(WindowInsets.Type.navigationBars())
            window.insetsController?.hide(WindowInsets.Type.statusBars())
        }else
        {
            window.setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        }
        determine()
        splasAnim()
    }

    private fun determine() {
        _animation1 = AnimationUtils.loadAnimation(this, R.anim.splash_anim)
        _animation2 = AnimationUtils.loadAnimation(this, R.anim.splash_anim2)

        //hiding or showing view
        _splashBinding.splashLoadingText.visibility = View.GONE
    }

    private fun splasAnim() {
        _splashBinding.splashScreenTextview.animation = _animation1
        _splashBinding.splashCaptionTextview.animation = _animation1
        _splashBinding.splashCaptionTextview.animation.setAnimationListener(object : Animation.AnimationListener{
            override fun onAnimationStart(animation: Animation?) {
            }

            override fun onAnimationEnd(animation: Animation?) {
                val backgroundExecutor = Executors.newSingleThreadScheduledExecutor()
                backgroundExecutor.schedule({ //background thread
                    mainExecutor.execute { //ui thread
                        _splashBinding.splashLoadingText.animation = _animation2
                        _splashBinding.splashLoadingText.visibility = View.VISIBLE

                        val backgroundExecutor2 = Executors.newSingleThreadScheduledExecutor()
                        backgroundExecutor2.schedule({
                            mainExecutor.execute{
                                setValue()
                            }
                        }, 1000, TimeUnit.MILLISECONDS)

                        backgroundExecutor.shutdown()
                    }
                }, 1000, TimeUnit.MILLISECONDS)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })

//        Executors.newSingleThreadScheduledExecutor().schedule({
//            mainExecutor.execute {
//                setValue()
//                _splashBinding.splashLoadingText.isClickable = true
//            }
//
//        }, 2000, TimeUnit.MILLISECONDS)

    }

    private fun setValue() {
        _splashBinding.splashLoadingText.text = resources.getString(R.string.klik_lanjut)
        _splashBinding.splashRoot.setOnClickListener(this)
        _splashBinding.splashRoot.isClickable = true
    }

    override fun onClick(v: View?) {
        when (v!!.id) {
            R.id.splash_root -> {
                startActivity(Intent(this@SplashActivity, HomeActivity::class.java))
//                Toast.makeText(this, "diklik", Toast.LENGTH_SHORT).show()
            }
        }
    }
}