package com.practice.o_ai

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.practice.o_ai.databinding.ActivityMainBinding
import retrofit2.Retrofit

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // this is for the image
        binding.generateImage.setOnClickListener{startActivity(Intent(
            this,ImageGenerateActivity::class.java))}

        binding.chatWithBoat.setOnClickListener{startActivity(Intent(
            this,ChatActivity::class.java))}


    }
}