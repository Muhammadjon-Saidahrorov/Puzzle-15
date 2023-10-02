package uz.gita.puzzle15dracula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class EnterView extends AppCompatActivity {
    private MediaPlayer mediaOpen;
    private MediaPlayer mediaClose;
    private Button continueButton;
    private boolean logic;

    LocalStorge localStorge;
    private boolean music = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enter_view);

        localStorge = LocalStorge.getInstance();
        show();
        logic = localStorge.getBoolean("CONTINUE");

        continueButton = findViewById(R.id.continueButton);

        continueButton.setOnClickListener(v -> {
            if (music)
                mediaOpen.start();
            Intent intent = new Intent(this, Continue.class);
            startActivity(intent);
        });

        Button kirishButton = findViewById(R.id.kirishButton);
        kirishButton.setOnClickListener(v -> {
            if (music)
                mediaOpen.start();
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        Button recordButton = findViewById(R.id.recordButton);
        recordButton.setOnClickListener(v -> {
            if (music) {
                mediaOpen.start();
            }
            Intent intent = new Intent(this, RecordActivity.class);
            startActivity(intent);
        });

        Button infoButton = findViewById(R.id.infoButton);
        infoButton.setOnClickListener(v -> {
            if (music) {
                mediaOpen.start();
            }
            Intent intent = new Intent(this, InfoActivity.class);
            startActivity(intent);
        });

        Button exitButton = findViewById(R.id.exitButton);
        exitButton.setOnClickListener(v -> {
            if (music)
                mediaClose.start();
            finish();
        });

        mediaOpen = MediaPlayer.create(this, R.raw.click_open);
        mediaClose = MediaPlayer.create(this, R.raw.click_close);

    }
    private void show(){
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.continueButton));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.kirishButton));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.recordButton));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.infoButton));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.exitButton));
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {

        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onResume() {
        music = localStorge.getBoolean("MUSIC");
        logic = localStorge.getBoolean("CONTINUE");
        if(!logic){
            continueButton.setVisibility(View.VISIBLE);
        }else continueButton.setVisibility(View.GONE);
        super.onResume();
    }
}