package com.example.calculadoraapp

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ListView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity

class History : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_history)

        val button = findViewById<Button>(R.id.backToHome)
        val historyList = findViewById<ListView>(R.id.historyList)

        val list = intent.getStringArrayListExtra("calculatorHistory")
        if (list != null) {
            var arrayAdapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, list)
            historyList.adapter = arrayAdapter
        }

        button.setOnClickListener {
            val mainIntent = Intent(this, MainActivity::class.java)
            mainIntent.putStringArrayListExtra("calculatorHistory", list)
            startActivity(mainIntent)
        }
    }
}