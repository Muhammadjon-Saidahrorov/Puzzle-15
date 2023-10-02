package uz.gita.puzzle15dracula;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.widget.TextView;

public class RecordActivity extends AppCompatActivity {
    private MediaPlayer mediaClose;
    private boolean music = true;
    LocalStorge localStorge;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);

        mediaClose = MediaPlayer.create(this, R.raw.click_close);
        localStorge = LocalStorge.getInstance();
        TextView score1 = findViewById(R.id.score1);
        TextView score2 = findViewById(R.id.score2);
        TextView score3 = findViewById(R.id.score3);
        score1.setText(String.valueOf(localStorge.getInt("Score1")));
        score2.setText(String.valueOf(localStorge.getInt("Score2")));
        score3.setText(String.valueOf(localStorge.getInt("Score3")));

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