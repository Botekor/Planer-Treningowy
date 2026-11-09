package com.example.planertreningowy;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView imageCwiczenia;
    private TextView nazwaCwiczenia;
    private TextView licznikSerii;
    private TextView lacznaLiczbaSerii;

    private Button losujCwiczenie;
    private Button minus;
    private Button plus;
    private Button nastepneCwiczenie;
    private Button reset;

    private int aktualnaLiczbaSerii = 0;
    private int sumaSerii = 0;

    private Random random = new Random();

    private final int[] obrazyCwiczen = {
            R.drawable.cwiczenie1,
            R.drawable.cwiczenie2,
            R.drawable.cwiczenie3,
            R.drawable.cwiczenie4,
            R.drawable.cwiczenie5,
            R.drawable.cwiczenie6
    };

    private final String[] nazwyCwiczen = {
            "Przysiad",
            "Pompki",
            "Plank",
            "Wykroki",
            "Burpees",
            "Podciąganie"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        WindowCompat.enableEdgeToEdge(getWindow());
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageCwiczenia = findViewById(R.id.imageView);
        nazwaCwiczenia = findViewById(R.id.textView);
        licznikSerii = findViewById(R.id.textView3);
        lacznaLiczbaSerii = findViewById(R.id.textView5);

        losujCwiczenie = findViewById(R.id.button2);
        minus = findViewById(R.id.button4);
        plus = findViewById(R.id.button5);
        nastepneCwiczenie = findViewById(R.id.button6);
        reset = findViewById(R.id.button7);

        ustawStanPoczatkowy();

        losujCwiczenie.setOnClickListener(v -> {
            losujNoweCwiczenie();
        });

        plus.setOnClickListener(v -> {
            aktualnaLiczbaSerii++;
            aktualizujLicznikSerii();
        });

        minus.setOnClickListener(v -> {
            if (aktualnaLiczbaSerii > 0) {
                aktualnaLiczbaSerii--;
                aktualizujLicznikSerii();
            }
        });

        nastepneCwiczenie.setOnClickListener(v -> {
            sumaSerii += aktualnaLiczbaSerii;

            aktualizujLacznaLiczbeSerii();

            losujNoweCwiczenie();
        });

        reset.setOnClickListener(v -> {
            ustawStanPoczatkowy();
        });
    }

    private void ustawStanPoczatkowy() {

        aktualnaLiczbaSerii = 0;
        sumaSerii = 0;

        imageCwiczenia.setImageResource(R.drawable.cwiczenie0);

        nazwaCwiczenia.setText("Losuj ćwiczenie");

        aktualizujLicznikSerii();
        aktualizujLacznaLiczbeSerii();
    }

    private void losujNoweCwiczenie() {

        int numerCwiczenia = random.nextInt(6);

        imageCwiczenia.setImageResource(
                obrazyCwiczen[numerCwiczenia]);

        nazwaCwiczenia.setText(
                nazwyCwiczen[numerCwiczenia]);

        aktualnaLiczbaSerii = 0;

        aktualizujLicznikSerii();
    }

    private void aktualizujLicznikSerii() {

        licznikSerii.setText(
                String.valueOf(aktualnaLiczbaSerii));
    }

    private void aktualizujLacznaLiczbeSerii() {

        lacznaLiczbaSerii.setText(
                "Łączna liczba serii: " + sumaSerii);
    }
}
