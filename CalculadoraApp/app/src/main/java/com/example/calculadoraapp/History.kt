package com.example.calculadoraapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val button = findViewById<Button>(R.id.backToHome)
        val historyList = findViewById<RecyclerView>(R.id.historyList)
        val list = intent.getStringArrayListExtra("calculatorHistory") ?: arrayListOf()
        historyList.layoutManager = LinearLayoutManager(this)
        val adapter = HistoryAdapter(list)
        historyList.adapter = adapter

        button.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putStringArrayListExtra("calculatorHistory", list)
            startActivity(mainIntent)
        }
    }
}