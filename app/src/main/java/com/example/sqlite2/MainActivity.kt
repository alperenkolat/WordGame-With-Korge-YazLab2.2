package com.example.sqlite2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dbHelper = DatabaseHelper(this)
        val db = dbHelper.writableDatabase

        dbHelper.copyDatabase(this)

        val nameEditText = findViewById<EditText>(R.id.name_edit_text)
        val addButton = findViewById<Button>(R.id.add_button)
        val searchButton = findViewById<Button>(R.id.search_button)


        searchButton.setOnClickListener {
            val name = nameEditText.text.toString()
            val dbHelper = DatabaseHelper(this)
            if (dbHelper.searchName(name)) {
                Toast.makeText(this, "$name bulundu", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "$name bulunamadı", Toast.LENGTH_SHORT).show()
            }


            db.close()
        }
    }
}