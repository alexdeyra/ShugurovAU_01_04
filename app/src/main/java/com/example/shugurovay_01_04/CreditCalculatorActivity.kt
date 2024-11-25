package com.example.shugurovay_01_04

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.InputFilter
import android.text.Spanned
import android.widget.Button
import android.widget.EditText
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class CreditCalculatorActivity : AppCompatActivity() {
    // Объявляем переменные для TextView, SeekBar, EditText и Button
    private lateinit var textViewLoanAmount: TextView  // Поле для отображения суммы кредита
    private lateinit var seekBarLoanAmount: SeekBar  // Ползунок для выбора суммы кредита
    private lateinit var editTextLoanTerm: EditText  // Поле для ввода срока кредита
    private lateinit var buttonCalculate: Button  // Кнопка для расчета ежемесячного платежа
    private lateinit var buttonBack: Button  // Объявляем переменную для кнопки "Назад"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Вызываем метод родительского класса
        setContentView(R.layout.activity_credit_calculator)  // Устанавливаем разметку для этой активности

        // Инициализируем переменные, связывая их с элементами разметки по идентификаторам
        textViewLoanAmount = findViewById(R.id.textViewLoanAmount)  // Находим TextView для отображения суммы кредита
        seekBarLoanAmount = findViewById(R.id.seekBarLoanAmount)  // Находим SeekBar для выбора суммы кредита
        editTextLoanTerm = findViewById(R.id.editTextLoanTerm)  // Находим EditText для ввода срока кредита
        buttonCalculate = findViewById(R.id.buttonCalculate)  // Находим кнопку для расчета
        buttonBack = findViewById(R.id.buttonBack)  // Инициализируем кнопку "Назад"

        // Установка фильтра для ввода только цифр в поле срока кредита
        editTextLoanTerm.filters = arrayOf(InputFilter { source, start, end, dest, dstart, dend ->
            for (i in start until end) {
                if (!Character.isDigit(source[i])) {  // Проверяем, является ли символ цифрой
                    return@InputFilter ""  // Запретить ввод, если символ не цифра
                }
            }
            null  // Разрешить ввод, если все символы цифры
        })

        // Установка слушателя для SeekBar
        seekBarLoanAmount.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                // Обновляем текстовое поле с суммой кредита при изменении ползунка
                textViewLoanAmount.text = "Сумма кредита: $progress"
            }

            override fun onStartTrackingTouch(seekBar: SeekBar?) {
                // Метод вызывается, когда пользователь начинает перемещать ползунок
            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
                // Метод вызывается, когда пользователь прекращает перемещение ползунка
            }
        })

        // Обработчик нажатия на кнопку "Назад"
        buttonBack.setOnClickListener {
            // Переход на предыдущую активность (BankActivity)
            val intent = Intent(this, BankActivity::class.java)
            startActivity(intent)  // Запускаем BankActivity
            finish()  // Закрываем текущую активность, если нужно
        }

        // Устанавливаем обработчик нажатия на кнопку "Рассчитать"
        buttonCalculate.setOnClickListener {
            val loanAmount = seekBarLoanAmount.progress  // Получаем сумму кредита из SeekBar
            val loanTerm = editTextLoanTerm.text.toString()  // Получаем срок кредита из EditText

            // Проверяем, заполнено ли поле срока кредита
            if (loanTerm.isEmpty()) {
                Toast.makeText(this, "Введите срок кредита", Toast.LENGTH_SHORT).show()  // Уведомление об ошибке
            } else {
                // Преобразуем срок кредита в число
                val termInMonths = loanTerm.toInt()  // Преобразуем строку в целое число
                // Простой расчет ежемесячного платежа
                val monthlyPayment = loanAmount / termInMonths  // Делим сумму кредита на срок в месяцах

                // Показать уведомление с ежемесячным платежом
                Toast.makeText(this, "Ежемесячный платеж: $monthlyPayment", Toast.LENGTH_SHORT).show()

                // Задержка перед переходом на экран сводки
                Handler().postDelayed({
                    val intent = Intent(this, SummaryActivity::class.java)  // Создаем Intent для перехода на SummaryActivity
                    // Передаем данные в Intent
                    intent.putExtra("LOAN_AMOUNT", loanAmount)  // Передаем сумму кредита
                    intent.putExtra("LOAN_TERM", termInMonths)  // Передаем срок кредита
                    intent.putExtra("MONTHLY_PAYMENT", monthlyPayment)  // Передаем ежемесячный платеж
                    startActivity(intent)  // Запускаем новую активность
                    finish()  // Закрываем текущую активность, если нужно
                }, 3000)  // Задержка в 3 секунды
            }
        }
    }
}