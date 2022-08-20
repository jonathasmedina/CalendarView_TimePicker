package com.example.calendarview;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    CalendarView calendar1;
    Button exibeTimePickerButton;
    int hora, minuto;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Log.e("", String.valueOf(Locale.getDefault())); -- en_US

        calendar1 = (CalendarView) findViewById(R.id.calendarView1);
        exibeTimePickerButton = findViewById(R.id.buttonTimePicker);

        //exemplo - exibindo um TimePicker
        exibeTimePickerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //argumentos: contexto da classe, ouvinte, hora, minuto, 24horas
                TimePickerDialog timePickerDialog = new TimePickerDialog(
                        MainActivity.this,
                        new TimePickerDialog.OnTimeSetListener() { // ou "ouvinteObj", e instancia embaixo
                            @Override
                            public void onTimeSet(TimePicker timePicker, int i, int i1) {
                                hora = i;
                                minuto = i1;
                                exibeTimePickerButton.setText(String.format(Locale.getDefault(),
                                        "%02d:%02d",
                                        hora,
                                        minuto));
                            }
                        },
                        hora,
                        minuto,
                        true);

                timePickerDialog.setTitle("Escolha a hora");
                timePickerDialog.show();
            }
        });

        //Exemplo - manipulando um calendarView
        calendar1.setOnDateChangeListener(new CalendarView.OnDateChangeListener() {
            @Override
            public void onSelectedDayChange(@NonNull CalendarView calendarView, int i, int i1, int i2) {
                //i = ano; i1 = mês do ano; i2 = dia do mês

                Calendar agora = new GregorianCalendar(i, i1, i2);

                Toast.makeText(getApplicationContext(), agora.getTime().toString(), Toast.LENGTH_LONG)
                        .show();
            }
        });

        /*alguns pickers customizados:
        https://android-arsenal.com/tag/27
        https://android-arsenal.com/details/1/8339
        https://android-arsenal.com/details/1/8045*/

    }
}