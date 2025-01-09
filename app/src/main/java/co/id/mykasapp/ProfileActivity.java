package co.id.mykasapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import co.id.mykasapp.MainActivity;

public class ProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST = 1; // Kode untuk memilih gambar dari galeri

    private ImageView profileImageView;
    private EditText fullNameInput, emailOrMobileInput, phoneNumberInput;
    private Button saveButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Inisialisasi elemen tampilan
        profileImageView = findViewById(R.id.profile_image);
        fullNameInput = findViewById(R.id.full_name_input);
        emailOrMobileInput = findViewById(R.id.email_or_mobile_input);
        phoneNumberInput = findViewById(R.id.phone_number_input);
        saveButton = findViewById(R.id.save_button);

        // Memuat data profil yang sudah disimpan sebelumnya
        loadProfileData();

        // Fungsi tombol kembali
        findViewById(R.id.back_button).setOnClickListener(view -> {
            // Kembali ke MainActivity atau HomeActivity
            Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        });

        // Mengganti foto profil dengan memilih dari galeri
        profileImageView.setOnClickListener(view -> openImageChooser());

        // Fungsi tombol Simpan
        saveButton.setOnClickListener(view -> {
            // Ambil data dari kolom input
            String fullName = fullNameInput.getText().toString();
            String emailOrMobile = emailOrMobileInput.getText().toString();
            String phoneNumber = phoneNumberInput.getText().toString();

            // Validasi input (semua kolom harus diisi)
            if (fullName.isEmpty() || emailOrMobile.isEmpty() || phoneNumber.isEmpty()) {
                Toast.makeText(ProfileActivity.this, "Harap isi semua kolom", Toast.LENGTH_SHORT).show();
            } else {
                // Simpan data ke SharedPreferences
                saveProfileData(fullName, emailOrMobile, phoneNumber);
                Toast.makeText(ProfileActivity.this, "Profil berhasil disimpan", Toast.LENGTH_SHORT).show();
            }
        });
    }

    // Membuka galeri untuk memilih gambar
    private void openImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*"); // Menentukan tipe file yang ingin dipilih (gambar)
        intent.setAction(Intent.ACTION_GET_CONTENT); // Aksi untuk mengambil konten
        startActivityForResult(Intent.createChooser(intent, "Pilih Gambar"), PICK_IMAGE_REQUEST);
    }

    // Menghandle hasil dari galeri
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            Uri imageUri = data.getData(); // URI gambar yang dipilih
            try {
                // Mengubah URI menjadi Bitmap
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), imageUri);
                profileImageView.setImageBitmap(bitmap); // Menampilkan gambar di ImageView
            } catch (IOException e) {
                e.printStackTrace();
                Toast.makeText(this, "Gagal memuat gambar", Toast.LENGTH_SHORT).show();
            }
        }
    }

    // Menyimpan data profil ke SharedPreferences
    private void saveProfileData(String fullName, String emailOrMobile, String phoneNumber) {
        getSharedPreferences("MyProfile", MODE_PRIVATE)
                .edit()
                .putString("FullName", fullName) // Menyimpan nama lengkap
                .putString("EmailOrMobile", emailOrMobile) // Menyimpan email atau nomor HP
                .putString("PhoneNumber", phoneNumber) // Menyimpan nomor telepon
                .apply(); // Menerapkan perubahan
    }

    // Memuat data profil dari SharedPreferences
    private void loadProfileData() {
        // Membaca data yang disimpan
        String fullName = getSharedPreferences("MyProfile", MODE_PRIVATE).getString("FullName", "");
        String emailOrMobile = getSharedPreferences("MyProfile", MODE_PRIVATE).getString("EmailOrMobile", "");
        String phoneNumber = getSharedPreferences("MyProfile", MODE_PRIVATE).getString("PhoneNumber", "");

        // Menampilkan data ke kolom input
        fullNameInput.setText(fullName);
        emailOrMobileInput.setText(emailOrMobile);
        phoneNumberInput.setText(phoneNumber);
    }
}
