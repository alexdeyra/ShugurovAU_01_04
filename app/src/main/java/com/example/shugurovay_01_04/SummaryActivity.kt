package com.example.shugurovay_01_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SummaryActivity : AppCompatActivity() {
    // Объявляем переменные для TextView и Button
    private lateinit var textViewSummary: TextView  // Поле для отображения сводной информации о кредите
    private lateinit var buttonRegister: Button  // Кнопка для перехода к регистрации

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Вызываем метод родительского класса
        setContentView(R.layout.activity_summary)  // Устанавливаем разметку для этой активности

        // Инициализируем переменные, связывая их с элементами разметки по идентификаторам
        textViewSummary = findViewById(R.id.textViewSummary)  // Находим TextView для отображения сводной информации
        buttonRegister = findViewById(R.id.buttonRegister)  // Находим кнопку для регистрации

        // Получаем данные из Intent, переданные из предыдущей активности
        val loanAmount = intent.getIntExtra("LOAN_AMOUNT", 0)  // Получаем сумму кредита
        val loanTerm = intent.getIntExtra("LOAN_TERM", 0)  // Получаем срок кредита
        val monthlyPayment = intent.getIntExtra("MONTHLY_PAYMENT", 0)  // Получаем ежемесячный платеж

        // Формируем строку для отображения сводной информации
        val summaryText = "Сумма кредита: $loanAmount\nСрок кредита: $loanTerm месяцев\nЕжемесячный платеж: $monthlyPayment"
        textViewSummary.text = summaryText  // Устанавливаем текст в TextView

        // Устанавливаем обработчик нажатия на кнопку "Регистрация"
        buttonRegister.setOnClickListener {
            // Создаем Intent для перехода на BankActivity
            val intent = Intent(this, BankActivity::class.java)
            startActivity(intent)  // Запускаем новую активность
        }
    }
}