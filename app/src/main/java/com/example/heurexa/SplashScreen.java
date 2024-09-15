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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Enable full-screen mode
        EdgeToEdge.enable(this);

        // Set system bar insets for padding
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        ImageView img = findViewById(R.id.back);
        TextView Heurexa = findViewById(R.id.Heurexa);
        TextView smile = findViewById(R.id.smile);

        // Load and start animations
        Animation myanim = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bg);
        img.startAnimation(myanim);

        Animation tet = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bounce);
        Heurexa.startAnimation(tet);

        Animation tet2 = AnimationUtils.loadAnimation(SplashScreen.this, R.anim.bounce);
        smile.startAnimation(tet2);

        // Delay and navigate to the appropriate activity
        new Handler().postDelayed(() -> {
            // Check if the user is logged in (replace with your login logic)
            boolean isLoggedIn = checkLoginStatus();

            Intent intent;
            if (isLoggedIn) {
                // User is logged in, go to MainActivity
                intent = new Intent(SplashScreen.this, MainActivity.class);
            } else {
                // User is not logged in, go to LoginActivity
                intent = new Intent(SplashScreen.this, GetStarted.class);
            }
            startActivity(intent);
            finish();
        }, 4000); // Adjust the delay as needed
    }

    private boolean checkLoginStatus() {
        // Replace this with your actual login logic
        // For example, you might check a shared preference or database
        return false; // Assuming user is not logged in initially
    }
}