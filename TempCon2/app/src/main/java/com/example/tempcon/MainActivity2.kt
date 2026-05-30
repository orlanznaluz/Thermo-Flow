package com.example.tempcon // Replace with your actual package name

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.textfield.TextInputEditText
import java.text.DecimalFormat // For formatting the input number in the formula

class MainActivity2 : AppCompatActivity() {

    private lateinit var celsiusButton: Button
    private lateinit var fahrenheitButton: Button
    private lateinit var kelvinButton: Button
    private lateinit var firstConText: TextView
    private lateinit var secondConText: TextView
    private lateinit var firstSolution: TextView
    private lateinit var secondSolution: TextView
    private lateinit var firstAnswer: TextView
    private lateinit var secondAnswer: TextView
    private lateinit var editTextTemperature: TextInputEditText
    private lateinit var convertButton: Button

    private val originalButtonColor = Color.parseColor("#664FA3")
    private val clickedButtonColor = Color.parseColor("#161D6F")

    private enum class TempUnit {
        CELSIUS, FAHRENHEIT, KELVIN
    }
    private var currentSelectedUnit: TempUnit = TempUnit.CELSIUS

    // Store base formulas. These will be updated with the input value.
    private var baseFormula1: String = ""
    private var baseFormula2: String = ""
    private val numberFormatter = DecimalFormat("#.##") // To format the input number in the formula string

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        celsiusButton = findViewById(R.id.celsiusButton)
        fahrenheitButton = findViewById(R.id.fahrenheitButton)
        kelvinButton = findViewById(R.id.kelvinButton)
        firstConText = findViewById(R.id.firstConText)
        secondConText = findViewById(R.id.secondConText)
        firstSolution = findViewById(R.id.firstSolution)
        secondSolution = findViewById(R.id.secondSolution)
        firstAnswer = findViewById(R.id.firstAnswer)
        secondAnswer = findViewById(R.id.secondAnswer)
        editTextTemperature = findViewById(R.id.editTextTemperature)
        convertButton = findViewById(R.id.convertButton)

        celsiusButton.setOnClickListener {
            currentSelectedUnit = TempUnit.CELSIUS
            // Store base formulas and update UI
            baseFormula1 = "F = (C × 9/5) + 32"
            baseFormula2 = "K = C + 273.15"
            updateUIForSelectedUnit(
                selectedButton = celsiusButton,
                otherButton1 = fahrenheitButton,
                otherButton2 = kelvinButton,
                conversion1Unit = "Fahrenheit",
                conversion2Unit = "Kelvin",
                formula1ToShow = baseFormula1.replace("C", "Input"), // Show generic formula initially
                formula2ToShow = baseFormula2.replace("C", "Input")
            )
        }

        fahrenheitButton.setOnClickListener {
            currentSelectedUnit = TempUnit.FAHRENHEIT
            baseFormula1 = "C = (F − 32) × 5/9"
            baseFormula2 = "K = (F − 32) × 5/9 + 273.15"
            updateUIForSelectedUnit(
                selectedButton = fahrenheitButton,
                otherButton1 = celsiusButton,
                otherButton2 = kelvinButton,
                conversion1Unit = "Celsius",
                conversion2Unit = "Kelvin",
                formula1ToShow = baseFormula1.replace("F", "Input"),
                formula2ToShow = baseFormula2.replace("F", "Input")
            )
        }

        kelvinButton.setOnClickListener {
            currentSelectedUnit = TempUnit.KELVIN
            baseFormula1 = "C = K − 273.15"
            baseFormula2 = "F = (K − 273.15) × 9/5 + 32"
            updateUIForSelectedUnit(
                selectedButton = kelvinButton,
                otherButton1 = celsiusButton,
                otherButton2 = fahrenheitButton,
                conversion1Unit = "Celsius",
                conversion2Unit = "Fahrenheit",
                formula1ToShow = baseFormula1.replace("K", "Input"),
                formula2ToShow = baseFormula2.replace("K", "Input")
            )
        }

        convertButton.setOnClickListener {
            performConversion()
        }

        celsiusButton.performClick() // Set initial state
    }

    private fun updateUIForSelectedUnit(
        selectedButton: Button,
        otherButton1: Button,
        otherButton2: Button,
        conversion1Unit: String,
        conversion2Unit: String,
        formula1ToShow: String, // Formula to display initially (can be generic)
        formula2ToShow: String
    ) {
        selectedButton.backgroundTintList = android.content.res.ColorStateList.valueOf(clickedButtonColor)
        otherButton1.backgroundTintList = android.content.res.ColorStateList.valueOf(originalButtonColor)
        otherButton2.backgroundTintList = android.content.res.ColorStateList.valueOf(originalButtonColor)

        firstConText.text = "Conversion To $conversion1Unit"
        secondConText.text = "Conversion To $conversion2Unit"

        firstSolution.text = formula1ToShow
        secondSolution.text = formula2ToShow

        firstAnswer.text = ""
        secondAnswer.text = ""
        // Optional: Do not clear editTextTemperature here, or clear it based on preference
    }

    private fun performConversion() {
        val inputText = editTextTemperature.text.toString()
        if (inputText.isBlank()) {
            Toast.makeText(this, "Please enter a temperature", Toast.LENGTH_SHORT).show()
            firstAnswer.text = ""
            secondAnswer.text = ""
            // Reset solution text to base generic formulas if input is cleared
            when (currentSelectedUnit) {
                TempUnit.CELSIUS -> {
                    firstSolution.text = baseFormula1.replace("C", "Input")
                    secondSolution.text = baseFormula2.replace("C", "Input")
                }
                TempUnit.FAHRENHEIT -> {
                    firstSolution.text = baseFormula1.replace("F", "Input")
                    secondSolution.text = baseFormula2.replace("F", "Input")
                }
                TempUnit.KELVIN -> {
                    firstSolution.text = baseFormula1.replace("K", "Input")
                    secondSolution.text = baseFormula2.replace("K", "Input")
                }
            }
            return
        }

        val inputTemp: Double
        try {
            inputTemp = inputText.toDouble()
        } catch (e: NumberFormatException) {
            Toast.makeText(this, "Invalid temperature format", Toast.LENGTH_SHORT).show()
            firstAnswer.text = ""
            secondAnswer.text = ""
            // Reset solution text
            when (currentSelectedUnit) {
                TempUnit.CELSIUS -> {
                    firstSolution.text = baseFormula1.replace("C", "Input")
                    secondSolution.text = baseFormula2.replace("C", "Input")
                }
                TempUnit.FAHRENHEIT -> {
                    firstSolution.text = baseFormula1.replace("F", "Input")
                    secondSolution.text = baseFormula2.replace("F", "Input")
                }
                TempUnit.KELVIN -> {
                    firstSolution.text = baseFormula1.replace("K", "Input")
                    secondSolution.text = baseFormula2.replace("K", "Input") // This was the missing part
                }
            }
            return
        }

        // Format the input temperature for display in the formula
        val formattedInput = numberFormatter.format(inputTemp)

        when (currentSelectedUnit) {
            TempUnit.CELSIUS -> {
                val fahrenheit = (inputTemp * 9.0/5.0) + 32
                val kelvin = inputTemp + 273.15
                firstSolution.text = baseFormula1.replace("C", formattedInput)
                secondSolution.text = baseFormula2.replace("C", formattedInput)
                firstAnswer.text = String.format("%.2f °F", fahrenheit)
                secondAnswer.text = String.format("%.2f K", kelvin)
            }
            TempUnit.FAHRENHEIT -> {
                val celsius = (inputTemp - 32) * 5.0/9.0
                val kelvin = celsius + 273.15
                firstSolution.text = baseFormula1.replace("F", formattedInput)
                secondSolution.text = baseFormula2.replace("F", formattedInput)
                firstAnswer.text = String.format("%.2f °C", celsius)
                secondAnswer.text = String.format("%.2f K", kelvin)
            }
            TempUnit.KELVIN -> {
                val celsius = inputTemp - 273.15
                val fahrenheit = celsius * 9.0/5.0 + 32
                firstSolution.text = baseFormula1.replace("K", formattedInput)
                secondSolution.text = baseFormula2.replace("K", formattedInput)
                firstAnswer.text = String.format("%.2f °C", celsius)
                secondAnswer.text = String.format("%.2f °F", fahrenheit)
            }
        }
    }
}