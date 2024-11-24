package co.id.mykasapp;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    private EditText editTextEmail, editTextPassword;
    private ImageView iconTogglePassword;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Cek apakah user sudah login
        SharedPreferences preferences = getSharedPreferences("MyKasPreferences", MODE_PRIVATE);
        boolean isLoggedIn = preferences.getBoolean("isLoggedIn", false);

        if (isLoggedIn) {
            // Jika sudah login, langsung ke MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
            return;
        }

        // Tampilkan layout login
        setContentView(R.layout.activity_login);

        // Inisialisasi komponen UI
        editTextEmail = findViewById(R.id.editTextEmailOrMobile);
        editTextPassword = findViewById(R.id.editTextPassword);
        iconTogglePassword = findViewById(R.id.iconTogglePassword);
        Button loginButton = findViewById(R.id.buttonLogin);
        TextView textViewSignup = findViewById(R.id.textViewSignIn);

        // Toggle visibility password
        iconTogglePassword.setOnClickListener(v -> togglePasswordVisibility());

        // Login button action
        loginButton.setOnClickListener(v -> handleLogin());

        // Redirect ke SignUpActivity
        textViewSignup.setOnClickListener(v -> {
            Intent intent = new Intent(LoginActivity.this, SignUpActivity.class);
            startActivity(intent);
        });
    }

    private void togglePasswordVisibility() {
        if (editTextPassword.getInputType() == (InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD)) {
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_VISIBLE_PASSWORD);
            iconTogglePassword.setImageResource(R.drawable.ic_visibility);
        } else {
            editTextPassword.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_VARIATION_PASSWORD);
            iconTogglePassword.setImageResource(R.drawable.ic_visibility_off);
        }
        // Mengatur kursor di akhir teks
        editTextPassword.setSelection(editTextPassword.length());
    }

    private void handleLogin() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        // Ambil data email dan password yang disimpan saat signup
        SharedPreferences preferences = getSharedPreferences("MyKasPreferences", Context.MODE_PRIVATE);
        String savedEmail = preferences.getString("EMAIL", null);
        String savedPassword = preferences.getString("PASSWORD", null);

        // Tambahkan log untuk memeriksa nilai savedEmail dan savedPassword
        Log.d("LoginActivity", "Saved Email: " + savedEmail);
        Log.d("LoginActivity", "Saved Password: " + savedPassword);

        // Periksa apakah email dan password cocok
        if (savedEmail == null || savedPassword == null || savedEmail.isEmpty() || savedPassword.isEmpty()) {
            Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
        } else if (email.equals(savedEmail) && password.equals(savedPassword)) {
            // Login berhasil, simpan status login
            SharedPreferences.Editor editor = preferences.edit();
            editor.putBoolean("isLoggedIn", true);
            editor.apply();

            // Pindah ke MainActivity
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Toast.makeText(LoginActivity.this, "Email atau password salah", Toast.LENGTH_SHORT).show();
        }


    }
}
