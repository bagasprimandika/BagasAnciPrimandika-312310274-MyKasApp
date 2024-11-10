package co.id.mykasapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button profileButton = findViewById(R.id.buttonProfile);
        Button formKasButton = findViewById(R.id.buttonFormKas);

        profileButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            startActivity(intent);
        });

        formKasButton.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, FormKasActivity.class);
            startActivity(intent);
        });
    }
}
