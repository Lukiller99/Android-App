package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import org.joda.time.Period;
import org.joda.time.PeriodType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class Calculadora extends AppCompatActivity {
    Button bfecha,bhoy,bcal,batras;
    TextView ntext;
    DatePickerDialog.OnDateSetListener Nacimiento,Hoy;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculadora);
        bfecha=findViewById(R.id.bfecha);
        batras=findViewById(R.id.batras);
        bhoy= findViewById(R.id.bhoy);
        ntext=findViewById(R.id.ntext);
        bcal=findViewById(R.id.bcal);

        Calendar calendario = Calendar.getInstance();
        int anio = calendario.get(Calendar.YEAR);
        int mes = calendario.get(Calendar.MONTH);
        int dia = calendario.get(Calendar.DAY_OF_MONTH);

        batras.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });
        bfecha.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog elegirFecha = new DatePickerDialog(Calculadora.this,android.R.style.Theme_Holo_Dialog_MinWidth,Nacimiento,anio,mes,dia);
                elegirFecha.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                elegirFecha.show();
            }
        });
        Nacimiento = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month +1;
                String fechahoy= dayOfMonth+"/"+month+"/"+year;
                bfecha.setText(fechahoy);
            }
        };
        bhoy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerDialog fechaHoy = new DatePickerDialog(Calculadora.this,android.R.style.Theme_Holo_Dialog_MinWidth,Hoy,anio,mes,dia);
                fechaHoy.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                fechaHoy.show();
            }
        });
        Hoy = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month= month +1;
                String fechahoy= dayOfMonth+"/"+month+"/"+year;
                bhoy.setText(fechahoy);
            }
        };
        bcal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nombre= ntext.getText().toString();
                String nacimientoFecha = bfecha.getText().toString();
                String hoyFecha =bhoy.getText().toString();
                SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
                try {
                    Date fecha1= formato.parse(nacimientoFecha);
                    Date fecha2= formato.parse(hoyFecha);
                    long fechaInicio = fecha1.getTime();
                    long fechaFin=fecha2.getTime();

                    if(fechaInicio<=fechaFin){
                        Period periodo= new Period(fechaInicio,fechaFin, PeriodType.yearMonthDay());
                        int anios = periodo.getYears();
                        int meses= periodo.getMonths();
                        int dias= periodo.getDays();
                        String tiempoVida= nombre+" tienes "+anios+" años, "+meses+" meses y "+dias+" dias.";
                        AlertDialog.Builder emergente = new AlertDialog.Builder(Calculadora.this);
                        emergente.setTitle("Tu Edad es:");
                        emergente.setMessage(tiempoVida);
                        emergente.setPositiveButton("Aceptar", null);
                        AlertDialog dialog = emergente.create();
                        dialog.show();

                    }else{
                        Toast.makeText(getApplicationContext(),"La fecha de nacimiento no debe ser mayor a la fecha actual .",Toast.LENGTH_LONG).show();
                    }
                } catch (ParseException e) {
                    e.printStackTrace();
                }


            }

        });
    }
}