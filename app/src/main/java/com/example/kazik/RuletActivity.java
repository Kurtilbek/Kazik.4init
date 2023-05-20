package com.example.kazik;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class RuletActivity extends AppCompatActivity {

    DataBaseHelper databaseHelper;
    SQLiteDatabase db;
    TextView firstNumber, secondNumber, thirdNumber, balanceNumber;
    Button button;
    int startMoney = 10000;//стартовый капитал
    int j = 0,
    rng = 10,// диапозон чисел которые могут выпасть
    N = 10,//количество свапов числа при прокрутке
    spd = 1,//скорость прокруток
    cst = 100,//чена крутки
    prz = 10000;//выйгрыш
    //public SharedPreferences pref;
    //public final static String MONEY = "mon";
    Random r = new Random();

    int a, b, c;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rulet);

        button = findViewById(R.id.my_button);
        firstNumber = findViewById(R.id.Text_1);
        secondNumber = findViewById(R.id.Text_2);
        thirdNumber = findViewById(R.id.Text_3);
        balanceNumber = findViewById(R.id.Text_4);

        //pref = getSharedPreferences("moneyData", MODE_PRIVATE);
        //m = pref.getInt(MONEY, 10000);
        //alanceNumber.setText(String.valueOf(m));
        databaseHelper = new DataBaseHelper(getApplicationContext());
        db = databaseHelper.getReadableDatabase();
        databaseHelper.setMoney(db, 100000);
        startMoney = databaseHelper.getMoney();
        balanceNumber.setText(String.valueOf(startMoney));
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
    //    public void deletAll(){
//        SharedPreferences.Editor editor = pref.edit();
//        editor.clear();
//        editor.apply();
//    }
//    public void saveData(int dataToSave) {
//        SharedPreferences.Editor editor = pref.edit();
//        editor.putInt(MONEY, dataToSave);
//        editor.apply();
//    }
//    public void onClickdeletAll(View view) {
//        deletAll();
//    }

    @SuppressLint("DefaultLocale")
    public void Vichislenia(View v) throws InterruptedException {
        if ((j >= N && a == b && a == c) && a == 7 && b == 7 && c == 7) // если 777
        {
            startMoney = startMoney + prz * 7;
        }
        if ((j >= N && a == b && a == c) && (a != 7 && b != 7 && c != 7)) // если 111, 222, и тд
        {
            startMoney = startMoney + prz;
        }

        startMoney = startMoney - cst; //формула пройгрыша
        for (int i = 0; i < N; i++) {
            j++;
            a = r.nextInt(rng);
            b = r.nextInt(rng);
            c = r.nextInt(rng);
            firstNumber.setText(String.valueOf(a));
            secondNumber.setText(String.valueOf(b));
            thirdNumber.setText(String.valueOf(c));

            Thread.sleep(spd);


            if(startMoney <=0){
                button.setClickable(false);
                Toast.makeText(this,"А че с деньгами?", Toast.LENGTH_SHORT).show();
            }
        }
    }
//    @Override
//    protected void onDestroy() {
//        super.onDestroy();
//        saveData(m);
//    }
}