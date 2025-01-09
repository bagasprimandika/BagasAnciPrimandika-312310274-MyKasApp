package co.id.mykasapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignUpActivity extends AppCompatActivity {

    private EditText inputFullName, inputEmailMobile, inputPhoneNumber, inputPassword;
    private Button btnRegister;
    private TextView tvSignInRedirect;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize views
        inputFullName = findViewById(R.id.input_full_name);
        inputEmailMobile = findViewById(R.id.input_email_mobile);
        inputPhoneNumber = findViewById(R.id.input_phone_number);
        inputPassword = findViewById(R.id.input_password); // Menambahkan kolom password
        btnRegister = findViewById(R.id.btn_register);
        tvSignInRedirect = findViewById(R.id.tv_signin_redirect);

        // Set listeners
        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validateAndRegister();
            }
        });

        tvSignInRedirect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Redirect to sign in screen
                Toast.makeText(SignUpActivity.this, "Redirect to sign in", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void validateAndRegister() {
        String fullName = inputFullName.getText().toString().trim();
        String emailMobile = inputEmailMobile.getText().toString().trim();
        String phoneNumber = inputPhoneNumber.getText().toString().trim();
        String password = inputPassword.getText().toString().trim();


        if (TextUtils.isEmpty(fullName)) {
            inputFullName.setError("Full name is required");
            return;
        }

        if (TextUtils.isEmpty(emailMobile)) {
            inputEmailMobile.setError("Email or mobile is required");
            return;
        }

        if (TextUtils.isEmpty(phoneNumber)) {
            inputPhoneNumber.setError("Phone number is required");
            return;
        }

        if (TextUtils.isEmpty(password)) {
            inputPassword.setError("Password is required");
            return;
        }
        // Simpan data pengguna ke SharedPreferences
        SharedPreferences sharedPreferences = getSharedPreferences("MyKasApp", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("fullName", fullName);
        editor.putString("emailMobile", emailMobile);
        editor.putString("phoneNumber", phoneNumber);
        editor.putString("password", password);
        editor.apply(); // Jangan lupa untuk commit perubahan


        // Perform registration logic here
        Toast.makeText(this, "Registration successful", Toast.LENGTH_SHORT).show();

        // Redirect to login screen after successful registration
        Intent intent = new Intent(SignUpActivity.this, LoginActivity.class);
        startActivity(intent);
        finish(); // Close the SignUpActivity

    }
}
