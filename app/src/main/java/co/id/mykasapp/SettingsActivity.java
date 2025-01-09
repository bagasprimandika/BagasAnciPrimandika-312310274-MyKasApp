package co.id.mykasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class SettingsActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TextView tvAboutTitle, tvAboutDescription, tvLanguageTitle, tvVersionTitle, tvVersion;
    private Spinner spinnerLanguage;
    private BottomNavigationView bottomNavigationView;
    private ImageView homeIcon, reportIcon, settingsIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // Initialize views
        toolbar = findViewById(R.id.toolbar);
        tvAboutTitle = findViewById(R.id.tv_about_title);
        tvAboutDescription = findViewById(R.id.tv_about_description);
        tvLanguageTitle = findViewById(R.id.tv_language_title);
        tvVersionTitle = findViewById(R.id.tv_version_title);
        tvVersion = findViewById(R.id.tv_version);
        spinnerLanguage = findViewById(R.id.spinner_language);
        bottomNavigationView = findViewById(R.id.nav);
        homeIcon = findViewById(R.id.homeIcon);
        reportIcon = findViewById(R.id.reportIcon);
        settingsIcon = findViewById(R.id.settingsIcon);

        // Setup toolbar
        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle("Settings");
        }

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Handle back button click
            }
        });

        // Set click listeners for navigation icons
        homeIcon.setOnClickListener(v -> navigateToHome());
        reportIcon.setOnClickListener(v -> navigateToReport());
        settingsIcon.setOnClickListener(v -> navigateToSettings());

        // Setup the language spinner
        setupLanguageSpinner();
    }

    private void navigateToHome() {
        // Navigate to HomeActivity
        Intent intent = new Intent(this, MainActivity.class); // Assuming MainActivity is Home
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
        finish();
    }

    private void navigateToReport() {
        // Navigate to ReportActivity
        Intent intent = new Intent(this, ReportActivity.class);
        startActivity(intent);
    }

    private void navigateToSettings() {
        // Navigate to SettingsActivity
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void setupLanguageSpinner() {
        // Example: Add languages to spinner
        String[] languages = {"English", "Bahasa Indonesia"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, languages);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLanguage.setAdapter(adapter);

        // Optionally: Set default selection based on saved preferences
        // spinnerLanguage.setSelection(preferences.getSelectedLanguageIndex());
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
