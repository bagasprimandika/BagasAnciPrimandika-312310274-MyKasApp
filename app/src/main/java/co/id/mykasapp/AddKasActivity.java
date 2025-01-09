package co.id.mykasapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

import co.id.mykasapp.DetailKasActivity;

public class AddKasActivity extends AppCompatActivity {

    private EditText etNamaKas, etJumlahAnggota, etFrekuensi, etNominalPerOrang;
    private Button btnSimpan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addkas);

        // Inisialisasi View
        etNamaKas = findViewById(R.id.et_nama_kas);
        etJumlahAnggota = findViewById(R.id.JumlahAnggota);
        etFrekuensi = findViewById(R.id.Frekuensi_Pembayaran);
        etNominalPerOrang = findViewById(R.id.et_nominal);
        btnSimpan = findViewById(R.id.btn_create);

        // Button Simpan Listener
        btnSimpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Ambil data dari EditText
                String namaKas = etNamaKas.getText().toString();
                String jumlahAnggota = etJumlahAnggota.getText().toString();
                String frekuensi = etFrekuensi.getText().toString();
                String nominalPerOrang = etNominalPerOrang.getText().toString();

                // Kirim data ke DetailKasActivity
                Intent intent = new Intent(AddKasActivity.this, DetailKasActivity.class);
                intent.putExtra("NAMA_KAS", namaKas);
                intent.putExtra("JUMLAH_ANGGOTA", jumlahAnggota);
                intent.putExtra("FREKUENSI", frekuensi);
                intent.putExtra("NOMINAL_PER_ORANG", nominalPerOrang);
                startActivity(intent);
            }
        });
    }
}
