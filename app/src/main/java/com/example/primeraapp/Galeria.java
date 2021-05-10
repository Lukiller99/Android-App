package com.example.primeraapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.Timer;
import java.util.TimerTask;

public class Galeria extends AppCompatActivity {
    private ImageSwitcher imageSwitcher;
    private ImageSwitcher imageSwitcher2;
    private int[] galeria = { R.drawable.perro1, R.drawable.perro2, R.drawable.perro3 };
    private int[] galeria2 = { R.drawable.gato1, R.drawable.gato2, R.drawable.gato3 };
    private int posicion;
    private int posicion2;
    private static final int DURACION = 5000;
    private Timer timer = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_galeria);
        Button bperro = (Button) findViewById(R.id.bperro);
        Button bgato = (Button) findViewById(R.id.bgato);
        Button batras2 = (Button) findViewById(R.id.batras2);
        TextView ptext =(TextView)findViewById(R.id.ptext);
        TextView gtext =(TextView)findViewById(R.id.gtext);

        batras2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent (v.getContext(), MainActivity.class);
                startActivityForResult(intent, 0);
            }
        });

        imageSwitcher = (ImageSwitcher) findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView = new ImageView(Galeria.this);
                imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView;
            }
        });

        imageSwitcher2 = (ImageSwitcher) findViewById(R.id.imageSwitcher2);
        imageSwitcher2.setFactory(new ViewSwitcher.ViewFactory()
        {
            public View makeView()
            {
                ImageView imageView2 = new ImageView(Galeria.this);
                imageView2.setScaleType(ImageView.ScaleType.CENTER_CROP);
                return imageView2;
            }
        });

        Animation fadeIn = AnimationUtils.loadAnimation(Galeria.this, R.anim.fade_in);
        Animation fadeOut = AnimationUtils.loadAnimation(Galeria.this, R.anim.fade_out);
        imageSwitcher.setInAnimation(fadeIn);
        imageSwitcher.setOutAnimation(fadeOut);
        imageSwitcher2.setInAnimation(fadeIn);
        imageSwitcher2.setOutAnimation(fadeOut);
        timer = new Timer();

        bperro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher.setVisibility(View.VISIBLE);
                imageSwitcher2.setVisibility(View.INVISIBLE);
                ptext.setVisibility(View.VISIBLE);
                gtext.setVisibility(View.INVISIBLE);
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    public void run()
                    {
                        runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                imageSwitcher.setImageResource(galeria[posicion]);
                                posicion++;
                                if (posicion == galeria.length)
                                    posicion = 0;
                            }
                        });
                    }
                }, 0, DURACION);

            }
        });
        bgato.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageSwitcher2.setVisibility(View.VISIBLE);
                imageSwitcher.setVisibility(View.INVISIBLE);
                gtext.setVisibility(View.VISIBLE);
                ptext.setVisibility(View.INVISIBLE);
                timer.scheduleAtFixedRate(new TimerTask()
                {
                    public void run()
                    {
                        runOnUiThread(new Runnable()
                        {
                            public void run()
                            {
                                imageSwitcher2.setImageResource(galeria2[posicion2]);
                                posicion2++;
                                if (posicion2 == galeria2.length)
                                    posicion2 = 0;
                            }
                        });
                    }
                }, 0, DURACION);

            }
        });

    }
}