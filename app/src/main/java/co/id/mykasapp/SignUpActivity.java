package co.id.mykasapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUpActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Inisialisasi komponen UI
        editTextEmail = findViewById(R.id.editTextEmail);
        editTextPassword = findViewById(R.id.editTextPassword);
        Button signupButton = findViewById(R.id.buttonSignUp);

        // Sign up button action
        signupButton.setOnClickListener(v -> handleSignUp());
    }

    private void handleSignUp() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(SignUpActivity.this, "Email dan password harus diisi", Toast.LENGTH_SHORT).show();
            return;
        }

        // Simpan email dan password ke SharedPreferences
        SharedPreferences preferences = getSharedPreferences("MyKasPreferences", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("EMAIL", email);
        editor.putString("PASSWORD", password);
        editor.apply();

        Toast.makeText(SignUpActivity.this, "Pendaftaran berhasil! Silakan login.", Toast.LENGTH_SHORT).show();

        // Kembali ke LoginActivity
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Menutup SignUpActivity agar tidak bisa kembali ke halaman ini
    }
}
