package com.example.quanlycongviec;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MakeNewTaskActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.make_new_task);

        Button saveButton = findViewById(R.id.saveButton);  // lấy nút từ layout XML

        saveButton.setOnClickListener(v -> {
            Intent intent = new Intent(MakeNewTaskActivity.this, SeeWorkActivity.class);
            startActivity(intent);
        });
    }
}
