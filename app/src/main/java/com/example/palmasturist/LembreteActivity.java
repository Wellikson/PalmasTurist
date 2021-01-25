package com.example.palmasturist;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class LembreteActivity extends AppCompatActivity {
    private AlarmManager alarmManager;
    private PendingIntent pendingIntent;
    private TimePicker timePicker;
    private DatePicker datePicker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lembrete);
        getSupportActionBar().setTitle("Definir Lembrete");
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        datePicker = findViewById(R.id.datepicker);
        timePicker = findViewById(R.id.timepicker);
        timePicker.setIs24HourView(true);
        criarCanal();
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    public void disparar(View v) {
        Calendar calNow = Calendar.getInstance();
        Calendar calSet = (Calendar) calNow.clone();

        calSet.set(Calendar.YEAR, datePicker.getYear());
        calSet.set(Calendar.MONTH, datePicker.getMonth());
        calSet.set(Calendar.DATE, datePicker.getDayOfMonth());
        calSet.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
        calSet.set(Calendar.MINUTE, timePicker.getMinute());
        calSet.set(Calendar.SECOND, 0);
        calSet.set(Calendar.MILLISECOND, 0);

        if (calSet.compareTo(calNow) <= 0) {
            calSet.add(Calendar.DATE, 1);
        }
        AlertDialog alertDialog = new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Confirmar Lembrete")
                .setMessage("\nAlarme programado para o dia " + datePicker.getDayOfMonth() + " de " +mes(datePicker.getMonth()) +
                        " as " + timePicker.getHour() + ":" + timePicker.getMinute() + " min")
                .setPositiveButton("Confirmar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        chamarAlerta(calSet);
                        finish();
                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                })
                .show();
    }

    public void chamarAlerta(Calendar calendar) {
        alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
        Intent i = new Intent(getApplicationContext(), MyBroadcast.class);
        pendingIntent = PendingIntent.getBroadcast(getApplicationContext(), 0, i, 0);
        alarmManager.set(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

    }

    private void criarCanal() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            CharSequence nome = "canal1";
            String descricao = "descrição do canal 1";
            int importancia = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel canal = new NotificationChannel("2", nome, importancia);
            canal.setDescription(descricao);
            NotificationManager nm = getSystemService(NotificationManager.class);
            nm.createNotificationChannel(canal);
        }
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }

    public String mes(int nMes){
        String mes;
        if(nMes == 0){
            mes = "Janeiro";
        }else if(nMes == 1){
            mes = "Fevereiro";
        }else if(nMes == 2){
            mes = "Março";
        }else if(nMes == 3){
            mes = "Abril";
        }else if(nMes == 4){
            mes = "Maio";
        }else if(nMes == 5){
            mes = "Junho";
        }else if(nMes == 6){
            mes = "Julho";
        }else if(nMes == 7){
            mes = "Agosto";
        }else if(nMes == 8){
            mes = "Setembro";
        }else if(nMes == 9){
            mes = "Outubro";
        }else if(nMes == 10){
            mes = "Novembro";
        }else{
            mes = "Dezembro";
        }
        return mes;
    }
}