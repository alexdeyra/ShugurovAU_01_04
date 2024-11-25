package com.example.shugurovay_01_04

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity


class BankActivity : AppCompatActivity() {

    // Объявляем переменные для EditText и Button
    private lateinit var editTextLogin: EditText  // Поле для ввода логина
    private lateinit var editTextPassword: EditText  // Поле для ввода пароля
    private lateinit var buttonLogin: Button  // Кнопка для входа

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)  // Вызываем метод родительского класса
        setContentView(R.layout.activity_bank)  // Устанавливаем разметку для этой активности

        // Инициализируем переменные, связывая их с элементами разметки по идентификаторам
        editTextLogin = findViewById(R.id.editTextLogin)  // Находим EditText для логина
        editTextPassword = findViewById(R.id.editTextPassword)  // Находим EditText для пароля
        buttonLogin = findViewById(R.id.buttonLogin)  // Находим кнопку для входа

        // Устанавливаем обработчик нажатия на кнопку "Войти"
        buttonLogin.setOnClickListener {
            try {
                // Получаем текст из полей ввода
                val login = editTextLogin.text.toString()  // Получаем логин
                val password = editTextPassword.text.toString()  // Получаем пароль

                // Проверяем, заполнены ли поля логина и пароля
                if (login.isEmpty() || password.isEmpty()) {
                    Toast.makeText(this, "Введите логин и пароль", Toast.LENGTH_SHORT).show()  // Уведомление об ошибке
                } else if (login != "ects" || password != "ects2024") {  // Проверяем правильность логина и пароля
                    Toast.makeText(this, "Неверный логин или пароль", Toast.LENGTH_SHORT).show()  // Уведомление об ошибке
                } else {
                    // Если логин и пароль верные, переходим на CreditCalculatorActivity
                    val intent = Intent(this, CreditCalculatorActivity::class.java)  // Создаем Intent для перехода
                    startActivity(intent)  // Запускаем новую активность
                }
            } catch (e: Exception) {
                // Обрабатываем возможные исключения
                Toast.makeText(this, "Ошибка: ${e.message}", Toast.LENGTH_SHORT).show()  // Уведомление об ошибке
                e.printStackTrace()  // Выводим стек вызовов в лог для отладки
            }
        }
    }
}