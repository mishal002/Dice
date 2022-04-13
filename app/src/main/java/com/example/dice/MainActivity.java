package com.example.dice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Vibrator;
import android.speech.tts.TextToSpeech;
import android.view.View;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private ImageView img2_img,img1_img;
    private TextView total_txt;
    private Button button_btn;
    int[] Dice = {R.drawable.diceone, R.drawable.dicetwo, R.drawable.dicethree, R.drawable.dicefour, R.drawable.dicefive, R.drawable.dicesix};
    private RotateAnimation rotate;
    private TextView number1_txt,number2_txt;
    private TextToSpeech textToSpeech;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        blinding();

        button_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Random random = new Random();
                int n1 = random.nextInt(Dice.length);
                int n2 = random.nextInt(Dice.length);

                img1_img.setImageResource(Dice[n1]);
                img2_img.setImageResource(Dice[n2]);
                rotate = new RotateAnimation(1000 ,1000);
                rotate.setDuration(100);
                img1_img.startAnimation(rotate);
                img2_img.startAnimation(rotate);

                number1_txt.setText("" + (n1 + 1));
                number2_txt.setText("" + (n2 + 1));
                int a = Integer.parseInt(String.valueOf(n1));
                int b = Integer.parseInt(String.valueOf(n2));
                Vibrator vibe = (Vibrator) getSystemService(MainActivity.VIBRATOR_SERVICE);
                vibe.vibrate(100);
                int x = (a + 1) + (b + 1);
                total_txt.setText("" + x);
                textToSpeech.speak(total_txt.getText().toString(), TextToSpeech.QUEUE_FLUSH, null);


            }

        });
        textToSpeech = new TextToSpeech(getApplicationContext(), new TextToSpeech.OnInitListener() {
            @Override
            public void onInit(int i) {

                // if No error is found then only it will run
                if (i != TextToSpeech.ERROR) {
                    // To Choose language of speech
                    textToSpeech.setLanguage(Locale.UK);
                }
            }
        });
    }

    private void blinding() {
        img1_img=findViewById(R.id.img1_img);
        img2_img=findViewById(R.id.img2_img);
        total_txt=findViewById(R.id.total_txt);
        button_btn=findViewById(R.id.button_btn);
        number1_txt=findViewById(R.id.number1_txt);
        number2_txt=findViewById(R.id.number2_txt);
    }
//    void setImage1(int z) {
//        Glide.with(MainActivity.this)
//                .load(img1_img[z])
//                .placeholder(R.drawable.ic_launcher_background)
//                .into(image1_btn);


    }