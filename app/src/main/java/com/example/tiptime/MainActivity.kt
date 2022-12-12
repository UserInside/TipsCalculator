package com.example.tiptime

import android.content.Context
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.tiptime.databinding.ActivityMainBinding
import java.text.NumberFormat

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCalculate.setOnClickListener { calculateTip() }
        binding.costOfServiceEditText.setOnKeyListener { view, i, _ -> handleKeyEvent(view, i) }
    }

    private fun calculateTip() {
        val stringInTextField = binding.costOfServiceEditText.text.toString()
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null || cost == 0.0) {
            Toast.makeText(this, "Enter check value", Toast.LENGTH_SHORT).show()
            displayTips(0.0)
            return
        }
        val percentage = when (binding.tipsOptions.checkedRadioButtonId) {
            R.id.tips_option_20_percent -> 0.2
            R.id.tips_option_15_percent -> 0.15
            else -> 0.1
        }

        var tips = cost * percentage
        if (binding.switchRoundTips.isChecked) {
            tips = kotlin.math.ceil(tips)
        }

        displayTips(tips)

    }

    private fun displayTips(tips: Double) {
        val formattedTips = NumberFormat.getCurrencyInstance().format(tips)
        binding.tipsAmount.text = getString(R.string.tips_amount, formattedTips)
    }


    private fun handleKeyEvent(view: View, keyCode: Int): Boolean {
        if (keyCode == KeyEvent.KEYCODE_ENTER) {
            // Hide the keyboard
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(view.windowToken, 0)
            return true
        }
        return false
    }

}

