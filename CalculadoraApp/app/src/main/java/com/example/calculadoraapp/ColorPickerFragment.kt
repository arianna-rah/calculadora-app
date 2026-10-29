package com.example.calculadoraapp

import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.skydoves.colorpickerview.ColorPickerView
import com.skydoves.colorpickerview.listeners.ColorEnvelopeListener

class ColorPickerFragment: Fragment(R.layout.color_picker_with_data) {

    var color = 0

    var onColorChanged: (() -> Unit)? = null

    // just like the usual main activity, we can set up our listeners here!
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val colorPicker = view.findViewById<ColorPickerView>(R.id.colorPicker)
        val textView = view.findViewById<TextView>(R.id.colorValueText)

        colorPicker.setColorListener(ColorEnvelopeListener { envelope, fromUser ->
            color = envelope.color

            // set text of text (hex color)
            textView.text = (String.format("#%06X", 0xFFFFFF and color))

            // set color of text + background color
            val red = Color.red(color)
            val green = Color.green(color)
            val blue = Color.blue(color)
            val brightness = (red * 299 + green * 587 + blue * 114) / 1000

            textView.setTextColor(
                if (brightness > 128) Color.BLACK else Color.WHITE
            )

            textView.setBackgroundColor(color)

            // trigger a color change, if anyone picks this up
            // the main activity can!
            onColorChanged?.invoke()
        })
    }

}