package uz.gita.puzzle15dracula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    private MediaPlayer mediaClose;
    private boolean music = true;
    LocalStorge localStorge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        mediaClose = MediaPlayer.create(this, R.raw.click_close);
        localStorge = LocalStorge.getInstance();
        AppCompatButton replyButton = findViewById(R.id.replyButton);
        replyButton.setOnClickListener(v -> {
            if (music)
                mediaClose.start();
            finish();
        });
    }

    @Override
    protected void onResume() {
        music = localStorge.getBoolean("MUSIC");
        super.onResume();
    }
}