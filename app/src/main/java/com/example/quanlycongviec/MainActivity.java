package com.example.quanlycongviec;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    RecyclerView rvTasks;
    FloatingActionButton fabAddTask;
    TaskAdapter taskAdapter;
    ArrayList<String> taskList;  // Dữ liệu demo, danh sách tên công việc

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rvTasks = findViewById(R.id.rvTasks);
        fabAddTask = findViewById(R.id.fabAddTask);


        // Khởi tạo dữ liệu demo
        taskList = new ArrayList<>();
        taskList.add("Hoàn thành báo cáo");
        taskList.add("Họp nhóm lúc 3 giờ chiều");
        taskList.add("Gửi email khách hàng");

        // Setup RecyclerView
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        taskAdapter = new TaskAdapter(taskList);
        rvTasks.setAdapter(taskAdapter);

        fabAddTask.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, MakeNewTaskActivity.class);
            startActivity(intent);
        });

    }
}

