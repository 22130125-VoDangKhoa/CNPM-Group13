package com.example.quanlycongviec;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private RecyclerView rvTasks;
    private FloatingActionButton fabAddTask;
    private TaskAdapter taskAdapter;
    private ArrayList<String> taskList;

    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private Toolbar toolbar;
    private ActionBarDrawerToggle toggle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Toolbar
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        // DrawerLayout và NavigationView
        drawerLayout = findViewById(R.id.drawerLayout);
        navigationView = findViewById(R.id.navigationView);

        // Toggle để mở/đóng menu Drawer
        toggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close
        );
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        // Xử lý chọn item menu trong NavigationView
        navigationView.setNavigationItemSelectedListener(item -> {
            int id = item.getItemId();
            if (id == R.id.nav_home) {
                Toast.makeText(MainActivity.this, "Trang chủ", Toast.LENGTH_SHORT).show();
            } else if (id == R.id.nav_settings) {
                Toast.makeText(MainActivity.this, "Cài đặt", Toast.LENGTH_SHORT).show();
            }
            drawerLayout.closeDrawers(); // Đóng menu sau khi chọn
            return true;
        });

        // RecyclerView và FloatingActionButton
        rvTasks = findViewById(R.id.rvTasks);
        fabAddTask = findViewById(R.id.fabAddTask);

        // Khởi tạo dữ liệu mẫu
        taskList = new ArrayList<>();
        taskList.add("Hoàn thành báo cáo");
        taskList.add("Họp nhóm lúc 3 giờ chiều");
        taskList.add("Gửi email khách hàng");

        // Set Adapter và LayoutManager cho RecyclerView
        taskAdapter = new TaskAdapter(taskList);
        rvTasks.setLayoutManager(new LinearLayoutManager(this));
        rvTasks.setAdapter(taskAdapter);

        // Xử lý sự kiện nút thêm công việc
        fabAddTask.setOnClickListener(v ->
                Toast.makeText(MainActivity.this, "Chức năng thêm công việc sẽ làm sau", Toast.LENGTH_SHORT).show());
    }



}
