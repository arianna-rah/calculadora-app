package com.example.calculadoraapp

import android.content.res.ColorStateList
import android.graphics.Color
import android.graphics.drawable.GradientDrawable
import android.os.Bundle
import android.widget.Button
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

enum class ColorOption(val color: Int) {
    RED(Color.RED),
    BLUE(Color.BLUE),
    GREEN(Color.GREEN),
    YELLOW(Color.YELLOW),
    MAGENTA(Color.MAGENTA),
    WHITE(Color.WHITE),
    CYAN(Color.CYAN)
}

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

        var color = ColorOption.RED
        var color2 = ColorOption.RED
        val colorButton = findViewById<Button>(R.id.colorPicker)
        val color2Button = findViewById<Button>(R.id.colorPicker2)
        colorButton.setOnClickListener {
            color = ColorOption.entries.random()
            val background = GradientDrawable()
            background.shape = GradientDrawable.RECTANGLE
            background.cornerRadius = 12f
            background.setColor(color.color)
            background.setStroke(2, Color.WHITE)

            colorButton.backgroundTintList = ColorStateList.valueOf(color.color)

            val red = Color.red(color.color)
            val green = Color.green(color.color)
            val blue = Color.blue(color.color)

            val brightness = (red * 299 + green * 587 + blue * 114) / 1000

            colorButton.setTextColor(
                if (brightness > 128) Color.BLACK else Color.WHITE
            )
        }

        color2Button.setOnClickListener {
            color2 = ColorOption.entries.random()
            val background = GradientDrawable()
            background.shape = GradientDrawable.RECTANGLE
            background.cornerRadius = 12f
            background.setColor(color2.color)
            background.setStroke(2, Color.WHITE)

            color2Button.backgroundTintList = ColorStateList.valueOf(color2.color)

            val red2 = Color.red(color2.color)
            val green2 = Color.green(color2.color)
            val blue2 = Color.blue(color2.color)

            val brightness2 = (red2 * 299 + green2 * 587 + blue2 * 114) / 1000

            color2Button.setTextColor(
                if (brightness2 > 128) Color.BLACK else Color.WHITE
            )
        }

        val items = listOf("add", "subtract", "multiply", "divide")

        val adapter = ArrayAdapter(this, android.R.layout.simple_dropdown_item_1line, items)

        val autoCompleteTextView = findViewById<AutoCompleteTextView>(R.id.autoCompleteTextView)
        autoCompleteTextView.setAdapter(adapter)

    }
}