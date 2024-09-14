package com.example.heurexa;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class SplashScreen extends AppCompatActivity {
    ImageView img;
    TextView Heurexa,smile;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        img = findViewById(R.id.back);
        Heurexa=findViewById(R.id.Heurexa);
        smile=findViewById(R.id.smile);
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent i=new Intent(SplashScreen.this, MainActivity.class);
              startActivity(i);
              finish();
            }
        },2000);
        Animation myanim=AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bg);
        img.startAnimation(myanim);
        Animation tet=AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bounce);
        Heurexa.startAnimation(tet);
        Animation tet2=AnimationUtils.loadAnimation(SplashScreen.this,R.anim.bounce);
        smile.startAnimation(tet2);
    }
}