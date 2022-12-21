package com.example.chapter8

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.chapter8.databinding.ActivityMain2Binding

class MainActivity2 : AppCompatActivity() {
    private lateinit var binding: ActivityMain2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSend.setOnClickListener {

            when {
                binding.edName.length() < 1  ->  Toast.makeText(this, "請輸入姓名", Toast.LENGTH_SHORT).show()
                binding.edPhone.length() < 1 ->  Toast.makeText(this, "請輸入電話", Toast.LENGTH_SHORT).show()
                else -> {
                    val b = Bundle()
                    b.putString("name" , binding.edName.text.toString())
                    b.putString("phone", binding.edPhone.text.toString())

                    setResult(Activity.RESULT_OK, Intent().putExtras(b))
                    finish()
                }
            }
        }
    }
}