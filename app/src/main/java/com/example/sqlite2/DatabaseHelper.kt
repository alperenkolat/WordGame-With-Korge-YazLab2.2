package com.example.sqlite2

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper
import java.io.FileOutputStream

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
         const val DATABASE_VERSION = 1
         const val DATABASE_NAME = "word_list.db"
         const val TABLE_NAME = "words"
         const val COL_NAME = "word"

    }

    override fun onCreate(db: SQLiteDatabase?) {
        //val createTable = "CREATE TABLE IF NOT EXISTS $TABLE_NAME (" +
        //        "$COL_NAME TEXT)"
        //db?.execSQL(createTable)

    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        // tablo güncelleme işlemleri burada yapılabilir, şimdilik boş bırakalım.
    }

    fun insertData(name: String) {
        val db = this.writableDatabase
        val contentValues = ContentValues()
        contentValues.put(COL_NAME, name)
        db.insert(TABLE_NAME, null, contentValues)
    }

    fun searchName(name: String): Boolean {
        val db = this.readableDatabase
        val cursor = db.rawQuery("SELECT * FROM $TABLE_NAME WHERE $COL_NAME = ?", arrayOf(name))
        val found = cursor.count > 0
        cursor.close()
        db.close()
        return found
    }

    fun copyDatabase(context: Context) {
        val inputStream = context.assets.open(DatabaseHelper.DATABASE_NAME)
        val outputStream = FileOutputStream(context.getDatabasePath(DATABASE_NAME))

        inputStream.copyTo(outputStream)

        inputStream.close()
        outputStream.flush()
        outputStream.close()
    }

}
