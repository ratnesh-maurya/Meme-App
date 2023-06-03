package com.example.memeshare

import android.content.Intent
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Telephony.Sms.Draft
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.JsonObjectRequest
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target


class MainActivity : AppCompatActivity() {
    var currentimage: String?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        loadmeme()


    }
    private fun loadmeme()
    {var imageView: ImageView = findViewById(R.id.memeimage)
        var progroesbar: ProgressBar =findViewById(R.id.progressBar)
        progroesbar.visibility=View.VISIBLE
        val queue = Volley.newRequestQueue(this)
         var url = "https://meme-api.com/gimme"
        // Request a string response from the provided URL.
        val jsonObjectRequest = JsonObjectRequest(Request.Method.GET, url, null,
            { response ->
                currentimage=response.getString("url")

                Glide.with(this ).load(currentimage).listener(object:RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progroesbar.visibility=View.GONE
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {
                        progroesbar.visibility=View.GONE
                        return false
                    }
                }).into(imageView)
            },
            { error ->
                Toast.makeText(applicationContext, "Failed to fetch data. Please try again later.", Toast.LENGTH_SHORT).show()

            }
        )

        // Access the RequestQueue through your singleton class.
        // Add the request to the RequestQueue.
        queue.add(jsonObjectRequest)

    }
    fun sharememe(view: View) {
        val intent= Intent(Intent.ACTION_SEND)
        intent.type="text/plain"
        intent.putExtra(Intent.EXTRA_TEXT, "hey i got a cool meme $currentimage")
        val chooser=Intent.createChooser(intent,"share meme  using...." )
        startActivity(chooser)
    }
    fun nextmeme(view: View) {
        loadmeme()
    }
}