package com.example.memeshare

import android.animation.AnimatorSet
import android.animation.ObjectAnimator
import android.animation.PropertyValuesHolder
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AccelerateDecelerateInterpolator
import android.view.animation.AnimationSet
import android.view.animation.ScaleAnimation
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd


class splash : AppCompatActivity() {
    private val splashDelay: Long = 3000 // Delay in milliseconds

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        /* <----text animator---->   */
        val textView = findViewById<TextView>(R.id.textvieww)

        val fadeInAnimator = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1f)
        fadeInAnimator.duration = 1000

        val slideUpAnimator = ObjectAnimator.ofFloat(textView, "translationY", 100f, 0f)
        slideUpAnimator.duration = 2500

        val animatoSet = AnimatorSet()
        animatoSet.playTogether(fadeInAnimator, slideUpAnimator)
        animatoSet.start()


        /* <----logo animator---->*/
        val logoImageView = findViewById<ImageView>(R.id.logoImageView)

        val zoomInAnimator = ObjectAnimator.ofPropertyValuesHolder(
            logoImageView,
            PropertyValuesHolder.ofFloat("scaleX", 1.0f, 1.3f),
            PropertyValuesHolder.ofFloat("scaleY", 1.0f, 1.3f)
        )

        zoomInAnimator.duration = 1500
        zoomInAnimator.interpolator = AccelerateDecelerateInterpolator()

        val zoomOutAnimator = ObjectAnimator.ofPropertyValuesHolder(
            logoImageView,
            PropertyValuesHolder.ofFloat("scaleX", 1.3f, 1.0f),
            PropertyValuesHolder.ofFloat("scaleY", 1.3f, 1.0f)
        )

        zoomOutAnimator.duration = 1500
        zoomOutAnimator.interpolator = AccelerateDecelerateInterpolator()

        val animatorSet = AnimatorSet()
        animatorSet.playSequentially(zoomInAnimator, zoomOutAnimator)
        animatorSet.doOnEnd {
            animatorSet.start() // Restart the animation when it ends
        }
        animatorSet.start()

        /* delay handler*/
        Handler().postDelayed(
            {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
                finish()
            },
            splashDelay
        )
    }
}
