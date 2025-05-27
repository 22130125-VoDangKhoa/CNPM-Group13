package com.example.quanlycongviec.database;

import android.content.*;
import android.database.*;
import android.database.sqlite.*;

import com.example.quanlycongviec.model.Task;

import java.util.*;

public class TaskDatabaseHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "tasks.db";
    private static final int DATABASE_VERSION = 1;
    private static final String TABLE_NAME = "tasks";
    private static final String ID = "id";
    private static final String NAME = "name";

    public TaskDatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME +
                " (" + ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                NAME + " TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addTask(String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        db.insert(TABLE_NAME, null, values);
    }

    public void updateTask(int id, String name) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(NAME, name);
        db.update(TABLE_NAME, values, ID + "=?", new String[]{String.valueOf(id)});
    }

    // 5.1.4.  Hệ thống gọi phương thức xóa deleteTask(taskId) trong DatabaseHepler
    public void deleteTask(int id) {
        SQLiteDatabase db = getWritableDatabase();
        // 5.1.5.  Hệ thống thực hiện câu lệnh sql DELETE để xóa nhiệm vụ
        // 5.1.6.  Hệ thống trả về kết quả
        db.delete(TABLE_NAME, ID + "=?", new String[]{String.valueOf(id)});
    }

    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> list = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        while (cursor.moveToNext()) {
            int id = cursor.getInt(0);
            String name = cursor.getString(1);
            list.add(new Task(id, name));
        }
        cursor.close();
        return list;
    }
}
