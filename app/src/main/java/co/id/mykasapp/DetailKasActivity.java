package co.id.mykasapp;

import android.os.Bundle;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import co.id.mykasapp.R;

public class DetailKasActivity extends AppCompatActivity {

    private TextView tvNamaKas, tvJumlahAnggota, tvFrekuensi, tvNominalPerOrang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_kas);

        // Inisialisasi View
        tvNamaKas = findViewById(R.id.nama_kas);
        tvJumlahAnggota = findViewById(R.id.jumlah_anggota);
        tvFrekuensi = findViewById(R.id.frekuensi);
        tvNominalPerOrang = findViewById(R.id.nominal_per_orang);

        // Ambil data dari Intent
        String namaKas = getIntent().getStringExtra("NAMA_KAS");
        String jumlahAnggota = getIntent().getStringExtra("JUMLAH_ANGGOTA");
        String frekuensi = getIntent().getStringExtra("FREKUENSI");
        String nominalPerOrang = getIntent().getStringExtra("NOMINAL_PER_ORANG");

        // Tampilkan data di TextView
        tvNamaKas.setText("Nama kas: " + namaKas);
        tvJumlahAnggota.setText("Jumlah anggota: " + jumlahAnggota);
        tvFrekuensi.setText("Frekuensi pembayaran: " + frekuensi);
        tvNominalPerOrang.setText("Nominal/orang: " + nominalPerOrang);
    }
}
