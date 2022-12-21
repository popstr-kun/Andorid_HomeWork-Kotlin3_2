package com.example.chapter8

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.chapter8.databinding.ActivityMainBinding

@Suppress("DEPRECATION")
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: MyAdapter
    private val contacts = ArrayList<Contact>()

    @Deprecated("Deprecated in Java")
    @SuppressLint("NotifyDataSetChanged")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data:
    Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        data?.extras?.let {
            if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
                val name  = it.getString("name")  ?: return@let
                val photo = it.getString("phone") ?: return@let
                contacts.add(Contact(name, photo))
                adapter.notifyDataSetChanged()
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val linearLayoutManager = LinearLayoutManager(this)
        linearLayoutManager.orientation = LinearLayoutManager.VERTICAL
        binding.recyclerView.layoutManager = linearLayoutManager

        adapter = MyAdapter(contacts)
        binding.recyclerView.adapter = adapter

        binding.btnAdd.setOnClickListener {
            startActivityForResult(Intent(this, MainActivity2::class.java), 1)
        }
    }

    class MyAdapter(private val data: ArrayList<Contact>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        class ViewHolder(v: View): RecyclerView.ViewHolder(v) {

            val tvName   : TextView  = v.findViewById(R.id.tv_name)
            val tvPhone  : TextView  = v.findViewById(R.id.tv_phone)
            val imgDelete: ImageView = v.findViewById(R.id.img_delete)
        }

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(viewGroup: ViewGroup, position: Int):
                ViewHolder {
            val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.adapter_row, viewGroup, false)
            return ViewHolder(v)
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.tvName.text  = data[position].name
            holder.tvPhone.text = data[position].phone
            holder.imgDelete.setOnClickListener {
                data.removeAt(position)
                notifyDataSetChanged()
            }
        }
    }

    data class Contact (
        val name : String,
        val phone: String
    )
}