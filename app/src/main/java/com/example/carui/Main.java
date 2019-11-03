package com.example.carui;

import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.ImageView;

public class Main extends AppCompatActivity {
    ImageView carbackground;
    FragmentTransaction transaction;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        carbackground = findViewById(R.id.carbackground);
        final Body body=new Body();
        final Front front=new Front();
        final FrameLayout fragment = findViewById(R.id.fragment);
        transaction=getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.fragment,body);
        transaction.commit();
        final ImageButton rotatebutton=findViewById(R.id.rotatebutton);
        final ImageButton unrotatebutton=findViewById(R.id.unrotatebutton);
        rotatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation rotate = AnimationUtils.loadAnimation(Main.this,R.anim.rotate);
                rotate.setFillAfter(true);
                carbackground.startAnimation(rotate);
                rotatebutton.setVisibility(View.INVISIBLE);
                fragment.setVisibility(View.INVISIBLE);
                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment,front);
                        transaction.commit();
                    }
                },1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        unrotatebutton.setVisibility(View.VISIBLE);
                        fragment.setVisibility(View.VISIBLE);
                    }
                },1100);
            }
        });
        unrotatebutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Animation unrotate = AnimationUtils.loadAnimation(Main.this,R.anim.unrotate);
                unrotate.setFillAfter(true);
                carbackground.startAnimation(unrotate);
                unrotatebutton.setVisibility(View.INVISIBLE);
                fragment.setVisibility(View.INVISIBLE);

                Handler handler=new Handler();
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        transaction=getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.fragment,body);
                        transaction.commit();
                    }
                },1000);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        rotatebutton.setVisibility(View.VISIBLE);
                        fragment.setVisibility(View.VISIBLE);
                    }
                },1100);
            }
        });
    }
}
