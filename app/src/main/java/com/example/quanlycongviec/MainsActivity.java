package com.example.quanlycongviec;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.example.quanlycongviec.database.TaskDatabaseHelper;
import com.example.quanlycongviec.model.Task;

import java.util.ArrayList;

public class MainsActivity extends AppCompatActivity {

    ListView listView;
    TasksAdapter adapter;
    TaskDatabaseHelper dbHelper;
    ArrayList<Task> taskList;
    ImageButton btnAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        dbHelper = new TaskDatabaseHelper(this);
        listView = findViewById(R.id.listViewTasks);
        btnAdd = findViewById(R.id.btnAdd);
        loadTasks();

        btnAdd.setOnClickListener(v -> showTaskDialog(null));
    }

    private void loadTasks() {
        taskList = dbHelper.getAllTasks();
        adapter = new TasksAdapter(this, taskList, new TasksAdapter.TaskActionListener() {
            @Override
            public void onEdit(Task task) { showTaskDialog(task); }

            // 5.1.1.  Hệ thống gọi phương thức onDelete(Task task) từ lớp MainActivity để xử lí hành động xóa nhiệm vụ
            @Override
            public void onDelete(Task task) {
                // 5.1.2.  Hệ thống hiển thị hộp thoại dialog xác nhận: “Bạn có chắc chắn muốn xóa nhiệm vụ này không?”
                new AlertDialog.Builder(MainsActivity.this)
                        .setTitle("Xác nhận xóa")
                        .setMessage("Bạn có chắc muốn xóa nhiệm vụ này không?")
                        // 5.1.3.  Người dùng xác nhận “Xóa”
                        .setPositiveButton("Xóa", (dialog, which) -> {
                            dbHelper.deleteTask(task.getId());
                            taskList.remove(task);
                            // 5.1.7.  Hệ thống gọi phương thức notifyDataSetChanged() để cập nhật lại giao diện
                            adapter.notifyDataSetChanged();
                            Toast.makeText(MainsActivity.this, "Đã xóa nhiệm vụ", Toast.LENGTH_SHORT).show();
                        })
                        // 5.2.3.  Người dùng chọn “Hủy” trong hộp thoại xác nhận
                        // 5.2.4.  Hệ thống đóng hộp thoại, không có thay đổi nào diễn ra, chạy lại giao diện ban đầu
                        .setNegativeButton("Hủy", null)
                        .show();
            }
        });
        listView.setAdapter(adapter);
    }

    private void showTaskDialog(Task task) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(task == null ? "Thêm nhiệm vụ" : "Sửa nhiệm vụ");

        final EditText input = new EditText(this);
        if (task != null) input.setText(task.getName());
        builder.setView(input);

        builder.setPositiveButton("Lưu", (dialog, which) -> {
            String name = input.getText().toString();
            if (task == null) dbHelper.addTask(name);
            else dbHelper.updateTask(task.getId(), name);
            loadTasks();
        });
        builder.setNegativeButton("Hủy", null);
        builder.show();
    }
}