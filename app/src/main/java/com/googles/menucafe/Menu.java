package com.googles.menucafe;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class Menu extends AppCompatActivity {
    private date call = new date();
    private Handler handler = new Handler();
    private DBHelper dbHelper;
    private ShareP sharedPrefPrefences;

    private TextView tanggal, total, t1, t2, t3, t4, t5, t6, t7, t8;
    private EditText nama, nomor_meja;
    private Button penjelasan, pesan;
    private CheckBox batagor, cireng, nasi_goreng, kopi, capucino, tea, keju, salad;

    private int harga = 0;
    private int menu_1, menu_2, menu_3, menu_4, menu_5, menu_6, menu_7, menu_8;

    private String pesanan = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
        handler.postDelayed(runnable, 1000);
        dbHelper = new DBHelper(this);
        sharedPrefPrefences = new ShareP(this);
        final SQLiteDatabase db = dbHelper.getReadableDatabase();

        tanggal = findViewById(R.id.d_tanggal);
        t1 = findViewById(R.id.t1);
        t2 = findViewById(R.id.t2);
        t3 = findViewById(R.id.t3);
        t4 = findViewById(R.id.t4);
        t5 = findViewById(R.id.t5);
        t6 = findViewById(R.id.t6);
        t7 = findViewById(R.id.t7);
        t8 = findViewById(R.id.t8);

        batagor = findViewById(R.id.batagor );
        cireng = findViewById(R.id.cireng);
        nasi_goreng = findViewById(R.id.nasi_goreng);
        kopi = findViewById(R.id.batagor );
        capucino = findViewById(R.id.capucino);
        tea = findViewById(R.id.tea);
        keju = findViewById(R.id.keju);
        salad = findViewById(R.id.salad);

        total = findViewById(R.id.total);

        nama = findViewById(R.id.d_nama);
        nomor_meja = findViewById(R.id.nomor_meja );

        penjelasan = findViewById(R.id.penjelasan);
        penjelasan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Menu.this, Penjelasan.class));
            }
        });

        if (getIntent().getStringExtra("status").equalsIgnoreCase("update")) {
            Cursor _cursor = db.rawQuery("SELECT * FROM db_pesanan WHERE no_meja='" + getIntent().getStringExtra("no_meja") + "';", null);
            _cursor.moveToFirst();
            nama.setText(getIntent().getStringExtra("nama"));
            nama.setEnabled(false);
            harga = Integer.parseInt(getIntent().getStringExtra("total_harga"));
            total.setText(getIntent().getStringExtra("total_harga"));
            nomor_meja.setText(getIntent().getStringExtra("no_meja") + "");
            nomor_meja.setEnabled(false);
            if (Integer.parseInt(_cursor.getString(7)) != 0) {
                batagor.setChecked(true);
                t1.setText(_cursor.getString(7));
            }
            if (Integer.parseInt(_cursor.getString(8)) != 0) {
                cireng.setChecked(true);
                t2.setText(_cursor.getString(8));
            }
            if (Integer.parseInt(_cursor.getString(9)) != 0) {
                nasi_goreng.setChecked(true);
                t3.setText(_cursor.getString(9));
            }
            if (Integer.parseInt(_cursor.getString(10)) != 0) {
                kopi.setChecked(true);
                t4.setText(_cursor.getString(10));
            }
            if (Integer.parseInt(_cursor.getString(11)) != 0) {
                capucino.setChecked(true);
                t5.setText(_cursor.getString(11));
            }
            if (Integer.parseInt(_cursor.getString(12)) != 0) {
                tea.setChecked(true);
                t6.setText(_cursor.getString(12));
            }
            if (Integer.parseInt(_cursor.getString(13)) != 0) {
                keju.setChecked(true);
                t7.setText(_cursor.getString(13));
            }
            if (Integer.parseInt(_cursor.getString(14)) != 0) {
                salad.setChecked(true);
                t8.setText(_cursor.getString(14));
            }
        }


        pesan = findViewById(R.id.pesan);
        pesan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (nama.getText().toString().isEmpty()) {
                    nama.setError("Silakan masukan nama anda!");
                    Toast.makeText(Menu.this, "Silakan masukan Nama terlebih dahulu!", Toast.LENGTH_SHORT).show();
                } else if (nomor_meja.getText().toString().isEmpty()) {
                    nomor_meja.setError("Silakan masukan Nomor meja anda!");
                    Toast.makeText(Menu.this, "Silakan masukan Nomo meja anda terlebih dahulu!", Toast.LENGTH_SHORT).show();
                } else {
                    if (batagor.isChecked() || cireng.isChecked() || nasi_goreng.isChecked() || kopi.isChecked() || capucino.isChecked() || tea.isChecked() || keju.isChecked() || salad.isChecked()) {
                        if (batagor.isChecked() && Integer.parseInt(t1.getText().toString()) > 0) {
                            menu_1 = Integer.parseInt(t1.getText().toString());
                            pesanan += "  - Batagor => " + t1.getText() + "\n";
                        }
                        if (cireng.isChecked() && Integer.parseInt(t2.getText().toString()) > 0) {
                            menu_2 = Integer.parseInt(t2.getText().toString());
                            pesanan += "  - Cireng => " + t2.getText() + "\n";
                        }
                        if (nasi_goreng.isChecked() && Integer.parseInt(t3.getText().toString()) > 0) {
                            menu_3 = Integer.parseInt(t3.getText().toString());
                            pesanan += "  - Nasi Goreng => " + t3.getText() + "\n";
                        }
                        if (kopi.isChecked() && Integer.parseInt(t4.getText().toString()) > 0) {
                            menu_4 = Integer.parseInt(t4.getText().toString());
                            pesanan += "  - Kopi Hitam => " + t4.getText() + "\n";
                        }
                        if (capucino.isChecked() && Integer.parseInt(t5.getText().toString()) > 0) {
                            menu_5 = Integer.parseInt(t5.getText().toString());
                            pesanan += "  - Cappuccino => " + t5.getText() + "\n";
                        }
                        if (tea.isChecked() && Integer.parseInt(t6.getText().toString()) > 0) {
                            menu_6 = Integer.parseInt(t6.getText().toString());
                            pesanan += "  - Sparkling Tea => " + t6.getText() + "\n";
                        }
                        if (keju.isChecked() && Integer.parseInt(t7.getText().toString()) > 0) {
                            menu_7 = Integer.parseInt(t7.getText().toString());
                            pesanan += "  - Cheese Cake => " + t7.getText() + "\n";
                        }
                        if (salad.isChecked() && Integer.parseInt(t8.getText().toString()) > 0) {
                            menu_8 = Integer.parseInt(t8.getText().toString());
                            pesanan += "  - Black Salad => " + t8.getText() + "\n";
                        }
                        if (Integer.parseInt(t1.getText().toString()) > 0 || Integer.parseInt(t2.getText().toString()) > 0 || Integer.parseInt(t3.getText().toString()) > 0 || Integer.parseInt(t4.getText().toString()) > 0 || Integer.parseInt(t5.getText().toString()) > 0 || Integer.parseInt(t6.getText().toString()) > 0 || Integer.parseInt(t7.getText().toString()) > 0 || Integer.parseInt(t8.getText().toString()) > 0) {
                            if (getIntent().getStringExtra("status").equalsIgnoreCase("update")) {

                                db.execSQL("UPDATE db_pesanan SET no_urut='Meja no - " + nomor_meja.getText().toString() + " : Nama - " + nama.getText().toString() + "',nama='" + nama.getText().toString() + "',tanggal='" + call.tanggal() + "',harga='" + total.getText().toString() + "',pesanan='" + pesanan + "',menu_1='" + menu_1 + "',menu_2='" + menu_2 + "',menu_3='" + menu_3 + "',menu_4='" + menu_4 + "',menu_5='" + menu_5 + "',menu_6='" + menu_6 + "',menu_7='" + menu_7 + "',menu_8='" + menu_8 + "' WHERE no_meja='" + getIntent().getStringExtra("no_meja") + "';");
                                Toast.makeText(Menu.this, "Pesanan sudah update dan di masukan ke daftar pesanan ^_^ TerimaKasih", Toast.LENGTH_SHORT).show();
                                Intent i;
                                i = new Intent(Menu.this, Pesanan.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                startActivity(i);

                            } else {

                                Cursor cursor = db.rawQuery("SELECT * FROM db_pesanan WHERE no_meja='" + nomor_meja.getText().toString() + "'", null);
                                cursor.moveToFirst();
                                if (cursor.getCount() < 1) {
                                    db.execSQL("INSERT INTO db_pesanan(no_meja,no_urut,nama,tanggal,harga,pesanan,menu_1,menu_2,menu_3,menu_4,menu_5,menu_6,menu_7,menu_8) VALUES ('" + nomor_meja.getText().toString() + "','Meja no - " + nomor_meja.getText().toString() + " : Nama - " + nama.getText().toString() + "','" + nama.getText().toString() + "','" + call.tanggal() + "','" + total.getText().toString() + "','" + pesanan + "','" + menu_1 + "','" + menu_2 + "','" + menu_3 + "','" + menu_4 + "','" + menu_5 + "','" + menu_6 + "','" + menu_7 + "','" + menu_8 + "');");
                                    Toast.makeText(Menu.this, "Pesanan sudah di masukan ke daftar pesanan ^_^ TerimaKasih", Toast.LENGTH_SHORT).show();
                                    Intent i;
                                    i = new Intent(Menu.this, Pesanan.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                                    startActivity(i);
                                } else {
                                    Toast.makeText(Menu.this, "Meja sudah di pesan silakan pilih meja yang lain ^_^", Toast.LENGTH_SHORT).show();
                                }
                            }
                        } else {
                            Toast.makeText(Menu.this, "Silakan masukan jumlah pesanan terlebih dahulu!", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Toast.makeText(Menu.this, "Harap pesan terlebih dahulu!", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    private Runnable runnable = new Runnable() {

        @Override
        public void run() {
            tanggal.setText("Tanggal         : " + call.tanggal());
            handler.postDelayed(this, 1000);
        }
    };

    //-------increment-------//
    public void i1(View view) {
        int i = Integer.parseInt(t1.getText().toString());
        if (batagor.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 25000;
                total.setText(harga + "");
                t1.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i2(View view) {
        int i = Integer.parseInt(t2.getText().toString());
        if (cireng.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 10000;
                total.setText(harga + "");
                t2.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i3(View view) {
        int i = Integer.parseInt(t3.getText().toString());
        if (nasi_goreng.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 50000;
                total.setText(harga + "");
                t3.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i4(View view) {
        int i = Integer.parseInt(t4.getText().toString());
        if (kopi.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 10000;
                total.setText(harga + "");
                t4.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i5(View view) {
        int i = Integer.parseInt(t5.getText().toString());
        if (capucino.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 20000;
                total.setText(harga + "");
                t5.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i6(View view) {
        int i = Integer.parseInt(t6.getText().toString());
        if (tea.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 15000;
                total.setText(harga + "");
                t6.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i7(View view) {
        int i = Integer.parseInt(t7.getText().toString());
        if (keju.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 40000;
                total.setText(harga + "");
                t7.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void i8(View view) {
        int i = Integer.parseInt(t8.getText().toString());
        if (salad.isChecked()) {
            if (i == 100) {
                Toast.makeText(this, "Maximal pesanan 100", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i += 1;
                harga += 30000;
                total.setText(harga + "");
                t8.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    //--------decrement---------//
    public void d1(View view) {
        int i = Integer.parseInt(t1.getText().toString());
        if (batagor.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 25000;
                total.setText(harga + "");
                t1.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d2(View view) {
        int i = Integer.parseInt(t2.getText().toString());
        if (cireng.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 10000;
                total.setText(harga + "");
                t2.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d3(View view) {
        int i = Integer.parseInt(t3.getText().toString());
        if (nasi_goreng.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 50000;
                total.setText(harga + "");
                t3.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }


    public void d4(View view) {
        int i = Integer.parseInt(t4.getText().toString());
        if (kopi.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 10000;
                total.setText(harga + "");
                t4.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d5(View view) {
        int i = Integer.parseInt(t5.getText().toString());
        if (capucino.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 20000;
                total.setText(harga + "");
                t5.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d6(View view) {
        int i = Integer.parseInt(t6.getText().toString());
        if (tea.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 15000;
                total.setText(harga + "");
                t6.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d7(View view) {
        int i = Integer.parseInt(t7.getText().toString());
        if (keju.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 40000;
                total.setText(harga + "");
                t7.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    public void d8(View view) {
        int i = Integer.parseInt(t8.getText().toString());
        if (salad.isChecked()) {
            if (i == 0) {
                Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
                return;
            } else {
                i -= 1;
                harga -= 30000;
                total.setText(harga + "");
                t8.setText(i + "");
            }
        } else {
            Toast.makeText(this, "Silakan pesan dahulu!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(new Intent(Menu.this, MainActivity.class)));
    }
}
