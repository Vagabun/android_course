package com.example.lab_2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MainActivity : AppCompatActivity() {

    private fun showSnack(it: View, msg: String) {
        Snackbar.make(it, msg, Snackbar.LENGTH_LONG).show()
    }

    private fun emptyCheck(operand1: String, operand2: String): Boolean {
        return (operand1 == "" || operand2 == "")
    }

    private fun calculator(operation: String, operand1: String, operand2: String): String {
        try {
            return when (operation) {
                "+" -> (operand1.toInt() + operand2.toInt()).toString()
                "-" -> (operand1.toInt() - operand2.toInt()).toString()
                "*" -> (operand1.toInt() * operand2.toInt()).toString()
                "/" -> (operand1.toDouble() / operand2.toDouble()).toString()
                else -> "0"
            }
        } catch (e: Exception) {
            throw Exception("Integer overflow")
        }
    }

    private fun calculatorHandler(operation: String, operand1: String, operand2: String, it: View): String {
        return try {
            calculator(operation, operand1, operand2)
        } catch (e: Exception) {
            showSnack(it, e.message.toString())
            "0"
        }
    }

    private fun viewHandler() {
        val edit1 = findViewById<EditText>(R.id.input_1)
        val edit2 = findViewById<EditText>(R.id.input_2)
        val plus = findViewById<Button>(R.id.plus_sign)
        val minus = findViewById<Button>(R.id.minus_sign)
        val multiply = findViewById<Button>(R.id.multiply_sign)
        val divide = findViewById<Button>(R.id.divide_sign)
        val answer = findViewById<TextView>(R.id.answer)

        plus.setOnClickListener {
            when {
                (emptyCheck(edit1.text.toString(), edit2.text.toString())) -> showSnack(
                    it,
                    "Error, one of the operands is empty"
                )
                else ->
                    answer.text = calculatorHandler("+", edit1.text.toString(), edit2.text.toString(), it)
            }
        }
        minus.setOnClickListener {
            when {
                (emptyCheck(edit1.text.toString(), edit2.text.toString())) -> showSnack(
                    it,
                    "Error, one of the operands is empty"
                )
                else ->
                    answer.text = calculatorHandler("-", edit1.text.toString(), edit2.text.toString(), it)
            }
        }
        multiply.setOnClickListener {
            when {
                (emptyCheck(edit1.text.toString(), edit2.text.toString())) -> showSnack(
                    it,
                    "Error, one of the operands is empty"
                )
                else ->
                    answer.text = calculatorHandler("*", edit1.text.toString(), edit2.text.toString(), it)
            }
        }
        divide.setOnClickListener {
            when {
                edit2.text.toString() == "0" -> showSnack(it, "Error, divide by zero")
                emptyCheck(edit1.text.toString(), edit2.text.toString()) -> showSnack(
                    it,
                    "Error, one of the operands is empty"
                )
                else -> answer.text = calculatorHandler("/", edit1.text.toString(), edit2.text.toString(), it)
            }
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewHandler()

    }
}
