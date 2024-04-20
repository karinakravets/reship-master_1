package kravets.k.v.reship

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.Button import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kravets.k.v.reship.databinding.ActivityMainBinding
import kotlin.random.Random
class MainActivity : AppCompatActivity() {
    private var correctAnswers = 0
    private var totalAnswers = 0
    private var incorrectAnswers = 0
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.startButton.setOnClickListener {
            generateExample()
            binding.startButton.isEnabled = false
            binding.answerInput.isEnabled = true
            binding.checkButton.isEnabled = true
        }
        binding.checkButton.setOnClickListener {
            binding.answerInput.isEnabled = false
            binding.checkButton.isEnabled = false
            val userAnswer = binding.answerInput.text.toString().toInt()
            val correctAnswer = calculateAnswer()
            if (userAnswer == correctAnswer) {
                binding.answerInput.setBackgroundColor(Color.GREEN)
                correctAnswers++
            } else {
                binding.answerInput.setBackgroundColor(Color.RED)
                incorrectAnswers++
            }
            totalAnswers++
            updateStats()
            binding.checkButton.isEnabled = false
            binding.startButton.isEnabled = true
            binding.answerInput.text.clear()
            binding.answerInput.setBackgroundColor(Color.TRANSPARENT)
        }
    }
    private fun generateExample() {
        val random = Random
        val num1 = random.nextInt(90) + 10
        val num2 = random.nextInt(90) + 10
        val operators = arrayOf('*', '/', '-', '+')
        val operation = operators.random()
        binding.operand1.text = num1.toString()
        binding.operand2.text = num2.toString()
        binding.operator.text = operation.toString()
    }
    private fun calculateAnswer(): Int {
        val num1 = binding.operand1.text.toString().toInt()
        val num2 = binding.operand2.text.toString().toInt()
        val operation = binding.operator.text.toString()
        return when (operation) {
            "*" -> num1 * num2
            "/" -> num1 / num2
            "-" -> num1 - num2
            "+" -> num1 + num2
            else -> -1
        }
    }
    private fun updateStats() {
        val accuracy = String.format("%.2f", (correctAnswers.toDouble() / totalAnswers) * 100)
        binding.textView11.text = "$correctAnswers"
        binding.textView12.text = "$incorrectAnswers"
        binding.statsTextview.text = accuracy
    }
}