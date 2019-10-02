package com.example.events_app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import kotlin.math.sqrt

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    //on calculate button
    fun getNumericInput(view: View) {
        val output = findViewById<TextView>(R.id.output)    //find output TextView

        val wrongInput = Toast.makeText(this, "Input two numbers only!", Toast.LENGTH_LONG)    //toast for error message

        //get the sides from the inputs with validation
        val sideA = getInputA()
        val sideB = getInputB()
        val sideC = getInputC()

        if(sideA == 0 && sideB == 0 && sideC == 0){     //validate all sides aren't zero
            wrongInput.show()

        } else if(sideA > 0 && sideB > 0 && sideC > 0){     //validate all sides don't have input above zero
            wrongInput.show()

        } else {
            val outputResult = performTheorem(sideA, sideB, sideC)  //send validated sides to calculation, one is 0
            output.text = outputResult.toString()   //display result in output TextView

        }
    }

    //gets input for side A; if is is blank, set it here to 0
    fun getInputA() : Int {
        val inputA = findViewById<EditText>(R.id.input_a)
        var sideA: Int
        //kotlin syntax simplified if/else
        if (inputA.text.isEmpty()) sideA = 0 else sideA = inputA.text.toString().toInt()
        return sideA
    }

    //gets input for side B; if is is blank, set it here to 0
    fun getInputB() : Int {

        val inputB = findViewById<EditText>(R.id.input_b)
        var sideB: Int
        if(inputB.text.isEmpty()) sideB = 0 else sideB = inputB.text.toString().toInt()
        return sideB
    }

    //gets input for side C; if is is blank, set it here to 0
    fun getInputC() : Int {

        val inputC = findViewById<EditText>(R.id.input_c)
        var sideC: Int
        if(inputC.text.isEmpty()) sideC = 0 else sideC = inputC.text.toString().toInt()
        return sideC
    }

    //calculation
    fun performTheorem(a: Int, b: Int, c: Int) : Double {

        var sideA: Int
        var sideB: Int
        var sideC: Int
        var result: Double = 0.0

        if(a != 0 && b != 0){   //side c is blank; square sides and square root sum
            sideA = a * a
            sideB = b * b
            sideC = sideA + sideB
            result = sideC.toDouble()
            result = sqrt(result)

        } else if(b != 0 && c != 0){    //side a is blank; square sides, subtract b from c, square root c
            sideB = b * b
            sideC = c * c
            sideC = sideC - sideB
            result = sideC.toDouble()
            result = sqrt(result)

        } else if(a != 0 && c != 0) {   //side b is blank; square sides, subtract a from c, square root c
            sideA = a * a
            sideC = c * c
            sideC = sideC - sideA
            result = sideC.toDouble()
            result = sqrt(result)
        }

        return result
    }


    //button entry to clear input fields after confirmation
    fun clearButton(view: View) {

        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setMessage(R.string.clearMessage)

        alertDialog.setPositiveButton(R.string.yes) { dialog, id -> clearFields() }

        alertDialog.setNegativeButton(R.string.no) { dialog, id -> dialog.cancel() }

        val alert = alertDialog.create()

        alert.setTitle(R.string.clearTitle)

        alert.show()

    }


    //clears all fields after confirmation received
    fun clearFields(){
        val inputA = findViewById<EditText>(R.id.input_a).apply {
            text.clear()
        }
        val inputB = findViewById<EditText>(R.id.input_b).apply {
            text.clear()
        }
        val inputC = findViewById<EditText>(R.id.input_c).apply {
            text.clear()
        }
        val output = findViewById<TextView>(R.id.output).apply {
            text = ""
        }
    }


    //closes app with warning dialogue; selecting no cancles cancel
    fun closeApp(view: View) {
        val alertDialog = AlertDialog.Builder(this)

        alertDialog.setMessage(R.string.exitMessage)

        alertDialog.setPositiveButton(R.string.yes) { dialog, id -> System.exit(0) }

        alertDialog.setNegativeButton(R.string.no) { dialog, id -> dialog.cancel() }

        val alert = alertDialog.create()

        alert.setTitle(R.string.exitTitle)

        alert.show()
    }
}
