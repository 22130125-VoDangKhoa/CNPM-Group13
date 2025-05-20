package com.example.quanlycongviec;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;

public class RegisterActivity extends AppCompatActivity {

    private EditText etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        mAuth = FirebaseAuth.getInstance();

        btnRegister.setOnClickListener(view -> {
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
//         2.5.Hệ thống kiểm tra có thông tin nào bị bỏ trống hoặc không hợp lệ không.
            if (TextUtils.isEmpty(email)) {
                etEmail.setError("Email không được để trống");
                return;
            }

            if (TextUtils.isEmpty(password)) {
                etPassword.setError("Mật khẩu không được để trống");
                return;
            }

            if (!password.equals(confirmPassword)) {
                etConfirmPassword.setError("Mật khẩu xác nhận không khớp");
                return;
            }

            mAuth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            //́2.8.Gửi email xác thực
                            mAuth.getCurrentUser().sendEmailVerification()
                                    .addOnCompleteListener(emailTask -> {
//                                        2.8.1.Nếu mail hợp lệ,hiển thị thông báo thành công và chuyển đến trang đăng nhập.
                                        if (emailTask.isSuccessful()) {

                                            Toast.makeText(this, "Đăng ký thành công. Vui lòng kiểm tra email để xác thực.", Toast.LENGTH_LONG).show();
                                            finish(); // Quay lại Login
                                        }
//                                        8.2.Nếu mail không hợp lệ,hiển thị thông báo lỗi
                                        else {
                                            Toast.makeText(this, "Không gửi được email xác thực.", Toast.LENGTH_SHORT).show();
                                        }
                                    });
                        } else {
                            Toast.makeText(this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            System.out.println(task.getException().getMessage());
                        }
                    });
        });
    }
}
