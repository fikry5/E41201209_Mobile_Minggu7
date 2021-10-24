package com.example.sqlite;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EditActivity extends AppCompatActivity {

    protected Cursor cursor;
    DataHelper dbHelper;
    Button btnKembali, btnPerbarui;
    EditText txtNomor, txtNama, txtTglLahir, txtJnsKelamin, txtAlamat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);

        // menginisialisasi
        dbHelper = new DataHelper(this);
        txtNomor = findViewById(R.id.txtNomor);
        txtNama = findViewById(R.id.txtNama);
        txtTglLahir = findViewById(R.id.txtTglLahir);
        txtJnsKelamin = findViewById(R.id.txtJnsKelamin);
        txtAlamat = findViewById(R.id.txtAlamat);
        btnKembali = findViewById(R.id.btnKembali);
        btnPerbarui = findViewById(R.id.btnPerbarui);

        SQLiteDatabase db = dbHelper.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM biodata WHERE nama = '" +
                getIntent().getStringExtra("nama") + "'", null);
        cursor.moveToFirst();
        if(cursor.getCount() > 0) {
            cursor.moveToPosition(0);
            txtNomor.setText(cursor.getString(0).toString());
            txtNama.setText(cursor.getString(1).toString());
            txtTglLahir.setText(cursor.getString(2).toString());
            txtJnsKelamin.setText(cursor.getString(3).toString());
            txtAlamat.setText(cursor.getString(4).toString());
        }

        // ketika menekan tombol kembali
        btnKembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                finish();
            }
        });

        btnPerbarui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View arg0) {
                // TODO Auto-generated method stub
                SQLiteDatabase db = dbHelper.getWritableDatabase();
                db.execSQL("UPDATE biodata SET " + // merubah data-data berdasarkan inputan
                        "nama='"+ txtNama.getText().toString() +"', " +
                        "tgl='" + txtTglLahir.getText().toString()+"', " +
                        "jk='"+ txtJnsKelamin.getText().toString() +"', " +
                        "alamat='" + txtAlamat.getText().toString() + "' " +
                        "WHERE no='" + txtNomor.getText().toString()+"'");
                Toast.makeText(getApplicationContext(), "Data berhasil diperbarui!", Toast.LENGTH_LONG).show();
                MainActivity.ma.RefreshList();
                finish();
            }
        });
    }
}