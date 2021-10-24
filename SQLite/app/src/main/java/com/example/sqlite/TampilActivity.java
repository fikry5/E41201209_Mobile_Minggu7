package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class TampilActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnKembali;
    TextView tvNomor, tvNama, tvTglLahir, tvJnsKelamin, tvAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil);

        // menginisialisasi
        dbHelper = new DataHelper(this);
        tvNomor = findViewById(R.id.tvNomor);
        tvNama = findViewById(R.id.tvNama);
        tvTglLahir = findViewById(R.id.tvTglLahir);
        tvJnsKelamin = findViewById(R.id.tvJnsKelamin);
        tvAlamat = findViewById(R.id.tvAlamat);
        btnKembali = findViewById(R.id.btnKembali);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // memanggil tanel berdasarkan atribut nama
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if (cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            tvNomor.setText(cursor.getString(0).toString());
            tvNama.setText(cursor.getString(1).toString());
            tvTglLahir.setText(cursor.getString(2).toString());
            tvJnsKelamin.setText(cursor.getString(3).toString());
            tvAlamat.setText(cursor.getString(4).toString());
        }

        // jika klik tombol kembali
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // menyelesaikan activity yang sekarang, dan menuju activity sebelumnya
            }
        });
    }
}