package com.example.calculadoraapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val colorPickerOne = findViewById<ColorPickerView>(R.id.colorPickerOne)
        var colorOne =  0
        val colorPickerTwo = findViewById<ColorPickerView>(R.id.colorPickerTwo)
        var colorTwo =  0

        val items = listOf("add", "subtract", "multiply", "divide")
        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)
        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView.setAdapter(adapter)
        var operation = items.get(0)
        autoCompleteTextView.setText(operation, false)

        val result = findViewById<TextView>(R.id.resultView)

        fun updateResult(selectedColor: Int) {
            val red2 = Color.red(selectedColor)
            val green2 = Color.green(selectedColor)
            val blue2 = Color.blue(selectedColor)
            val brightness2 = (red2 * 299 + green2 * 587 + blue2 * 114) / 1000

            result.setTextColor(
                if (brightness2 > 128) Color.BLACK else Color.WHITE
            )

            result.setBackgroundColor(selectedColor)
        }

        fun calculateAndUpdateResult() {
            val r1 = Color.red(colorOne)
            val g1 = Color.green(colorOne)
            val b1 = Color.blue(colorOne)

            val r2 = Color.red(colorTwo)
            val g2 = Color.green(colorTwo)
            val b2 = Color.blue(colorTwo)

            var resultR = 0
            var resultG = 0
            var resultB = 0

            when (operation) {
                "add" -> {
                    resultR = (r1 + r2)
                    resultG = (g1 + g2)
                    resultB = (b1 + b2)
                }
                "subtract" -> {
                    resultR = (r1 - r2)
                    resultG = (g1 - g2)
                    resultB = (b1 - b2)
                }
                "multiply" -> {
                    resultR = (r1 * r2)
                    resultG = (g1 * g2)
                    resultB = (b1 * b2)
                }
                "divide" -> {
                    resultR = if (r2 != 0) (r1 / r2) else 255
                    resultG = if (g2 != 0) (g1 / g2) else 255
                    resultB = if (b2 != 0) (b1 / b2) else 255
                }
            }

            // make sure the colors are in the proper range
            resultR = Math.min(Math.max(0, resultR), 255)
            resultG = Math.min(Math.max(0, resultG), 255)
            resultB = Math.min(Math.max(0, resultB), 255)

            val resultColor = Color.argb(255, resultR, resultG, resultB)
            updateResult(resultColor)
        }

        colorPickerOne.setColorListener(ColorEnvelopeListener { envelope, fromUser ->
            colorOne = envelope.color
            calculateAndUpdateResult()
        })

        colorPickerTwo.setColorListener(ColorEnvelopeListener { envelope, fromUser ->
            colorTwo = envelope.color
            calculateAndUpdateResult()
        })

        autoCompleteTextView.setOnItemClickListener { parent, view, position, id ->
            operation = parent.getItemAtPosition(position) as String
            calculateAndUpdateResult()
        }
    }
}