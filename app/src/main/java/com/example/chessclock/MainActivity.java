package com.example.chessclock;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.InputFilter;
import android.view.View;
import android.widget.EditText;
import android.widget.Switch;

import com.beardedhen.androidbootstrap.BootstrapButton;
import com.beardedhen.androidbootstrap.BootstrapEditText;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private BootstrapButton btnBullet1, btnBullet2, btnBullet3, btnBlitz1, btnBlitz2, btnBlitz3, btnRapida1, btnRapida2, btnRapida3, btnPersonalizado;

    private AlertDialog dialog;
    private  AlertDialog.Builder dialogBuilder;
    private BootstrapEditText edtTempoMinutos, edtTempoSegundos, edtAcrescimo;
    private BootstrapButton btnCancelar, btnIniciar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnBullet1 = findViewById(R.id.btnBullet1);
        btnBullet2 = findViewById(R.id.btnBullet2);
        btnBullet3 = findViewById(R.id.btnBullet3);
        btnBlitz1 = findViewById(R.id.btnBlitz1);
        btnBlitz2 = findViewById(R.id.btnBlitz2);
        btnBlitz3 = findViewById(R.id.btnBlitz3);
        btnRapida1 = findViewById(R.id.btnRapida1);
        btnRapida2 = findViewById(R.id.btnRapida2);
        btnRapida3 = findViewById(R.id.btnRapida3);
        btnPersonalizado = findViewById(R.id.btnPersonalizado);


        btnBullet1.setOnClickListener(this);
        btnBullet2.setOnClickListener(this);
        btnBullet3.setOnClickListener(this);
        btnBlitz1.setOnClickListener(this);
        btnBlitz2.setOnClickListener(this);
        btnBlitz3.setOnClickListener(this);
        btnRapida1.setOnClickListener(this);
        btnRapida2.setOnClickListener(this);
        btnRapida3.setOnClickListener(this);
        btnPersonalizado.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent it = new Intent(this, Clock.class);
        switch(v.getId()){
            case R.id.btnBullet1:
                it.putExtra("opcao", 1);
                startActivity(it);
                break;
            case R.id.btnBullet2:
                it.putExtra("opcao", 2);
                startActivity(it);
                break;
            case R.id.btnBullet3:
                it.putExtra("opcao", 3);
                startActivity(it);
                break;
            case R.id.btnBlitz1:
                it.putExtra("opcao", 4);
                startActivity(it);
                break;
            case R.id.btnBlitz2:
                it.putExtra("opcao", 5);
                startActivity(it);
                break;
            case R.id.btnBlitz3:
                it.putExtra("opcao", 6);
                startActivity(it);
                break;
            case R.id.btnRapida1:
                it.putExtra("opcao", 7);
                startActivity(it);
                break;
            case R.id.btnRapida2:
                it.putExtra("opcao", 8);
                startActivity(it);
                break;
            case R.id.btnRapida3:
                it.putExtra("opcao", 9);
                startActivity(it);
                break;
            case R.id.btnPersonalizado:
                criarPopup();
                break;
        }
    }

    public void criarPopup(){
        dialogBuilder = new AlertDialog.Builder(this);
        final  View partidaPersonalizadoPopup = getLayoutInflater().inflate(R.layout.popup, null);
        edtTempoMinutos = partidaPersonalizadoPopup.findViewById(R.id.edtTempoMinutos);
        edtTempoSegundos = partidaPersonalizadoPopup.findViewById(R.id.edtTempoSegundos);
        edtAcrescimo = partidaPersonalizadoPopup.findViewById(R.id.edtAcrescimo);
        btnCancelar = partidaPersonalizadoPopup.findViewById(R.id.btnCancelar);
        btnIniciar = partidaPersonalizadoPopup.findViewById(R.id.btnIniciar);

        edtTempoMinutos.setFilters(new InputFilter[] { new InputFilterMinMax("0", "60")});
        edtTempoSegundos.setFilters(new InputFilter[] { new InputFilterMinMax("0", "60")});
        edtAcrescimo.setFilters(new InputFilter[] { new InputFilterMinMax("0", "60")});

        btnIniciar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent it = new Intent(getBaseContext(), Clock.class);


                int minutos =  Integer.parseInt(edtTempoMinutos.getText().toString());
                int segundos = Integer.parseInt(edtTempoSegundos.getText().toString());
                int acrescimo = Integer.parseInt(edtAcrescimo.getText().toString());

                it.putExtra("minutos", minutos);
                it.putExtra("segundos", segundos);
                it.putExtra("acrescimo", acrescimo);
                it.putExtra("opcao", 10);

                startActivity(it);
            }
        });

        btnCancelar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.cancel();
            }
        });

        dialogBuilder.setView(partidaPersonalizadoPopup);
        dialog = dialogBuilder.create();
        dialog.show();
    }


}