package com.example.chessclock;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.text.DecimalFormat;

public class Clock extends AppCompatActivity implements View.OnClickListener {

    RelativeLayout btnUser1, btnUser2;
    TextView txtCronometro2, txtMilisegundos2, txtCronometro1, txtMilisegundos1;
    ImageView imgReload, imgSair;

    boolean timeRunning1;
    boolean timeRunning2;

    boolean iniciado;
    CountDownTimer countDownTimer1;
    CountDownTimer countDownTimer2;

    long tempoDecorrido1;
    long tempoDecorrido2;
    long acrescimo;

    private String tempo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clock);

        btnUser1 = findViewById(R.id.btnUser1);
        btnUser2 = findViewById(R.id.btnUser2);

        txtCronometro2 = findViewById(R.id.txtCronometro2);
        txtMilisegundos2 = findViewById(R.id.txtMilisegundos2);
        txtCronometro1 = findViewById(R.id.txtCronometro1);
        txtMilisegundos1 = findViewById(R.id.txtMilisegundos1);
        imgReload = findViewById(R.id.imgReload);
        imgSair = findViewById(R.id.imgSair);

        btnUser1.setOnClickListener(this);
        btnUser2.setOnClickListener(this);

        Intent intentLocal = getIntent();
        int opcaoEscolhida = intentLocal.getIntExtra("opcao", 0);

        acrescimo = 0;

        if(opcaoEscolhida == 1){
            tempoDecorrido1 = 60000;
            tempoDecorrido2 = 60000;
        }else if(opcaoEscolhida == 2){
            tempoDecorrido1 = 60000;
            tempoDecorrido2 = 60000;
            acrescimo = 1000;
        }else if(opcaoEscolhida == 3){
            tempoDecorrido1 = 120000;
            tempoDecorrido2 = 120000;
            acrescimo = 1000;
        }else if(opcaoEscolhida == 4){
            tempoDecorrido1 = 180000;
            tempoDecorrido2 = 180000;
        }else if(opcaoEscolhida == 5){
            tempoDecorrido1 = 180000;
            tempoDecorrido2 = 180000;
            acrescimo = 2000;
        }else if(opcaoEscolhida == 6){
            tempoDecorrido1 = 300000;
            tempoDecorrido2 = 300000;
        }else if(opcaoEscolhida == 7){
            tempoDecorrido1 = 600000;
            tempoDecorrido2 = 600000;
        }else if(opcaoEscolhida == 8){
            tempoDecorrido1 = 900000;
            tempoDecorrido2 = 900000;
        }else if(opcaoEscolhida == 9){
            tempoDecorrido1 = 1800000;
            tempoDecorrido2 = 1800000;
        }else if(opcaoEscolhida == 10){
            int tempoMinutos = intentLocal.getIntExtra("minutos", 0);
            int tempoSegundos = intentLocal.getIntExtra("segundos", 0);
            int tempoAcrescimo = intentLocal.getIntExtra("acrescimo", 0);

            tempoDecorrido1 = tempoMinutos*60000 + tempoSegundos * 1000;
            tempoDecorrido2 = tempoMinutos*60000 + tempoSegundos * 1000;
            acrescimo = tempoAcrescimo*1000;
        }

        tempo = textoCronometro(tempoDecorrido1);

        txtCronometro1.setText(tempo);
        txtCronometro2.setText(tempo);

        imgSair.setOnClickListener(this);
        imgReload.setOnClickListener(this);
    }

    @SuppressLint("ResourceAsColor")
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnUser1:
                if(iniciado){
                    if(timeRunning1){
                        trocarCor();
                        pause();
                    }
                }else{
                    iniciado = true;
                    startTimer2(tempoDecorrido2);
                }
                break;


            case R.id.btnUser2:
                if(iniciado){
                    if(timeRunning2){
                        pause();
                    }
                }else{
                    iniciado = true;
                    startTimer1(tempoDecorrido1);
                    trocarCor();
                }
                break;

            case R.id.imgReload:
                countDownTimer1.cancel();
                countDownTimer2.cancel();
                txtCronometro1.setText(tempo);
                txtCronometro2.setText(tempo);
                txtMilisegundos2.setText("000");
                txtMilisegundos1.setText("000");
                btnUser1.setBackgroundColor(getResources().getColor(R.color.clickOff));
                btnUser2.setBackgroundColor(getResources().getColor(R.color.clickOff));
                break;

            case R.id.imgSair:
                finish();
                break;
        }
    }

    @SuppressLint("ResourceAsColor")
    public void pause(){
        if(timeRunning1){
            countDownTimer1.cancel();
            timeRunning1 = false;
            tempoDecorrido1 += acrescimo;
            txtCronometro1.setText(textoCronometro(tempoDecorrido1));
            startTimer2(tempoDecorrido2);
        }else{
            countDownTimer2.cancel();
            timeRunning2 = false;
            tempoDecorrido2 += acrescimo;
            txtCronometro2.setText(textoCronometro(tempoDecorrido2));
            startTimer1(tempoDecorrido1);
        }
    }


    public void startTimer1(long tempo){
        countDownTimer1 = new CountDownTimer(tempo, 1){

            @SuppressLint("SetTextI18n")
            public void onTick(long millSeconds){
                tempoDecorrido1 = millSeconds;

                int minutes = (int) millSeconds/60000;
                int seconds = (int) millSeconds % 60000 / 1000;
                int milisegundos = (int) millSeconds%1000;

                String timeLeftText;
                timeLeftText = minutes+":";
                if (seconds < 10) timeLeftText += "0";
                timeLeftText += seconds;

                txtMilisegundos1.setText(Integer.toString(milisegundos));
                txtCronometro1.setText(timeLeftText);
            }
            @SuppressLint("SetTextI18n")
            public  void onFinish(){
                timeRunning1 = false;
            }
        }.start();
        timeRunning1 = true;
        trocarCor();
    }

    public void startTimer2(long tempo){
        countDownTimer2 = new CountDownTimer(tempo, 1){

            @SuppressLint("SetTextI18n")
            public void onTick(long millSeconds){
                tempoDecorrido2 = millSeconds;

                int minutes = (int) millSeconds/60000;
                int seconds = (int) millSeconds % 60000 / 1000;
                int milisegundos = (int) millSeconds%1000;

                String timeLeftText;
                timeLeftText = minutes+":";
                if (seconds < 10) timeLeftText += "0";
                timeLeftText += seconds;


                txtCronometro2.setText(timeLeftText);
                txtMilisegundos2.setText(Integer.toString(milisegundos));
            }
            @SuppressLint("SetTextI18n")
            public  void onFinish(){
                timeRunning2 = false;
            }
        }.start();
        timeRunning2 = true;
        trocarCor();
    }

    public String textoCronometro(long tempo){
        int minutes = (int) tempo/60000;
        int seconds = (int) tempo % 60000 / 1000;

        String timeLeftText;
        timeLeftText = minutes+":";
        if (seconds < 10) timeLeftText += "0";
        timeLeftText += seconds;

        return timeLeftText;
    }

    @SuppressLint("ResourceAsColor")
    public void trocarCor(){
        if(timeRunning1){
            btnUser1.setBackgroundColor(getResources().getColor(R.color.clickOff));
            btnUser2.setBackgroundColor(getResources().getColor(R.color.clickOn));
        }else{
            btnUser1.setBackgroundColor(getResources().getColor(R.color.clickOn));
            btnUser2.setBackgroundColor(getResources().getColor(R.color.clickOff));
        }
    }
}