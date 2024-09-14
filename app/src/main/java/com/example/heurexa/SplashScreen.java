package com.example.heurexa;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreen extends AppCompatActivity {

    private TextView textHeurexa;
    private TextView textSmileCapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        // Find views from the layout
        ImageView rightImage = findViewById(R.id.right_image);
        textHeurexa = findViewById(R.id.text_heurexa);
        textSmileCapture = findViewById(R.id.text_smile_capture);

        // Load animations from XML resources
        Animation slideRightFull = AnimationUtils.loadAnimation(this, R.anim.slide_right);

        // Start the right image animation immediately
        rightImage.startAnimation(slideRightFull);

        // Set a listener for the end of the animation
        slideRightFull.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                // Animation started
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                // Start letter-by-letter animation after the right image animation ends
                animateText("Heurexa", textHeurexa, 100);  // Display "Heurexa" with a 100ms delay
                new Handler().postDelayed(() -> animateText("Smile and Capture", textSmileCapture, 100), 1000);  // Start after "Heurexa"

                // Start checking login status immediately after the "Smile and Capture" animation ends
                new Handler().postDelayed(() -> checkLoginStatus(), 1100 + (textSmileCapture.getText().length() * 100)); // Calculate total time for the last animation
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
                // Animation repeated
            }
        });
    }

    // Method to animate text letter by letter
    private void animateText(String text, TextView textView, int delay) {
        textView.setText("");  // Clear the TextView
        textView.setVisibility(View.VISIBLE);  // Make the TextView visible
        new Handler().postDelayed(new Runnable() {
            int index = 0;

            @Override
            public void run() {
                if (index < text.length()) {
                    textView.append(String.valueOf(text.charAt(index)));  // Append each letter
                    index++;
                    new Handler().postDelayed(this, delay);  // Delay for the next letter
                }
            }
        }, delay);  // Start delay
    }

    // Method to check login status
    private void checkLoginStatus() {
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        boolean isLoggedIn = sharedPreferences.getBoolean("isLoggedIn", false); // Default to false if not found

        Intent intent;
        if (isLoggedIn) {
            intent = new Intent(SplashScreen.this, MainActivity.class);
        } else {
            intent = new Intent(SplashScreen.this, GetStarted.class);
        }
        startActivity(intent);
        finish();  // Close this activity
    }
}
