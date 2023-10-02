package uz.gita.puzzle15dracula;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Chronometer;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Continue extends AppCompatActivity implements View.OnClickListener {

    private AppCompatButton[][] buttons;
    private TextView stepCounter;
    private Coordinate emptyCoordinate;
    private List<Integer> numbers;
    private int counter = 0;
    private boolean logic = true;
    private boolean logicPause = true;
    private MediaPlayer mediaButton;
    private MediaPlayer mediaShaffle;
    private MediaPlayer mediaWin;

    private MediaPlayer mediaOpen;
    private MediaPlayer mediaClose;
    private boolean music = true;
    private AppCompatButton musicButton;
    private AppCompatButton musicoffButton;
    private LinearLayout musicButtonIcon;
    private LocalStorge localStorge;
    private Chronometer chronometer;
    private long pauseTime;
    private boolean startLogic = true;
    private AppCompatButton replyButton;
    private AppCompatButton pauseButton;
    private AppCompatButton refreshButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        localStorge = LocalStorge.getInstance();
        load();
        initViews();
        initNumbers();
        setNumbersToButton();
        showBtn();
        clickBtns();
        startLogic = localStorge.getBoolean("STARTLOGIC");
        if(!startLogic) {
            getPositions();
        }
    }

    private void load(){
        mediaButton = MediaPlayer.create(this, R.raw.mixkit);
        mediaWin = MediaPlayer.create(this, R.raw.bg_won);
        mediaShaffle = MediaPlayer.create(this, R.raw.bg1);
        mediaOpen = MediaPlayer.create(this, R.raw.click_open);
        mediaClose = MediaPlayer.create(this, R.raw.click_close);

        music = localStorge.getBoolean("MUSIC");
        musicButton = findViewById(R.id.musicButton);
        musicoffButton = findViewById(R.id.musicoffButton);
        musicButtonIcon = findViewById(R.id.musicButtonIcon);

        if (music) {
            musicoffButton.setVisibility(View.INVISIBLE);
        } else {
            musicButtonIcon.setVisibility(View.INVISIBLE);
        }

        refreshButton = findViewById(R.id.refreshButton);
        replyButton = findViewById(R.id.replyButton);
        pauseButton = findViewById(R.id.pauseBnt);

        chronometer = findViewById(R.id.chhronometer);

    }

    private void clickBtns() {
        musicButton.setOnClickListener(v -> {
            if (music) {
                music = false;
                localStorge.saveBoolean("MUSIC", false);
                musicButtonIcon.setVisibility(View.INVISIBLE);
                musicoffButton.setVisibility(View.VISIBLE);
            }
        });

        musicoffButton.setOnClickListener(v -> {
            if (!music) {
                music = true;
                localStorge.saveBoolean("MUSIC", true);
                musicButtonIcon.setVisibility(View.VISIBLE);
                musicoffButton.setVisibility(View.INVISIBLE);
            }
        });

        replyButton.setOnClickListener(v -> {
            if (music)
                mediaClose.start();
            finish();
        });

        pauseButton.setOnClickListener(v -> {
            pauseTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            localStorge.saveLong("PAUSETIME", pauseTime);
            chronometer.stop();
            logicPause = false;
            pauseDialog();
        });

        refreshButton.setOnClickListener(v -> {
            showBtn();
            if (music)
                mediaShaffle.start();
            counter = 0;
            stepCounter.setText(String.valueOf(counter));
            setNumbersToButton();
            logic = true;
            chronometer.setBase(SystemClock.elapsedRealtime());
        });
    }

    private void showBtn() {
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn1));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn2));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn3));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn4));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn5));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn6));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn7));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn8));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn9));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn10));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn11));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn12));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn13));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn14));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn15));
        YoYo.with(Techniques.BounceIn)
                .duration(700)
                .repeat(0)
                .playOn(findViewById(R.id.btn16));
    }

    private void initViews() {
        RelativeLayout containerButtons = findViewById(R.id.containerButtons);
        stepCounter = findViewById(R.id.textStepCounter);

        int count = containerButtons.getChildCount();
        int size = 4;
        buttons = new AppCompatButton[size][size];

        for (int i = 0; i < count; i++) {
            int x = i / size;
            int y = i % size;
            AppCompatButton button = (AppCompatButton) containerButtons.getChildAt(i);
            button.setTag(new Coordinate(x, y));
            button.setOnClickListener(this);
            buttons[x][y] = button;
        }
        emptyCoordinate = new Coordinate(size - 1, size - 1);
    }

    private void initNumbers() {
        numbers = new ArrayList<>(15);
        for (int i = 1; i < 16; i++) {
            numbers.add(i);
        }
    }


    private void setNumbersToButton() {
        int size = 4;
        buttons[size - 1][size - 1].setVisibility(View.INVISIBLE);
        shaffleNumbers();
        stepCounter.setText(String.valueOf(counter));

        buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setVisibility(View.VISIBLE);
        emptyCoordinate.setX(size - 1);
        emptyCoordinate.setY(size - 1);
        buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setVisibility(View.INVISIBLE);

        for (int i = 0; i < buttons.length; i++) {
            for (int j = 0; j < buttons[i].length; j++) {
                int index = i * buttons.length + j;
                if (index < 15)
                    buttons[i][j].setText(String.valueOf(numbers.get(index)));
            }
        }
        buttons[3][3].setText(" ");
        counter = 0;
        stepCounter.setText(String.valueOf(counter));
        if (chronometer != null) {
            chronometer.setBase(SystemClock.elapsedRealtime());
            chronometer.start();
        }
    }

    private void shaffleNumbers() {
        Collections.shuffle(numbers);
        if (!isSolvable(numbers))
            shaffleNumbers();
    }

    private boolean isSolvable(List<Integer> numbers) {
        RelativeLayout containerButtons = findViewById(R.id.containerButtons);
        int count = containerButtons.getChildCount();

        int countInversions = 0;
        for (int i = 0; i < count - 1; i++) {
            for (int j = i; j < count - 1; j++) {
                if (numbers.get(i) > numbers.get(j))
                    countInversions++;
            }
        }
        return countInversions % 2 == 0;
    }


    @Override
    public void onClick(View view) {
        AppCompatButton button = (AppCompatButton) view;
        Coordinate clikedCoordinate = (Coordinate) button.getTag();
        int dX = Math.abs(emptyCoordinate.getX() - clikedCoordinate.getX());
        int dY = Math.abs(emptyCoordinate.getY() - clikedCoordinate.getY());

        if ((dX + dY == 1) && logic) {
            buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setText(button.getText());
            button.setText(" ");

            buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setVisibility(View.VISIBLE);
            emptyCoordinate.setX(clikedCoordinate.getX());
            emptyCoordinate.setY(clikedCoordinate.getY());
            buttons[clikedCoordinate.getX()][clikedCoordinate.getY()].setVisibility(View.INVISIBLE);
            stepCounter.setText(String.valueOf(++counter));
            if (music)
                mediaButton.start();
            isWin();
        }
    }

    private void isWin() {
        RelativeLayout containerButtons = findViewById(R.id.containerButtons);
        int count = containerButtons.getChildCount();

        for (int i = 0; i < count - 1; i++) {
            if (buttons[i / 4][i % 4].getText().equals(String.valueOf(i + 1))) {
                logic = false;
            } else {
                logic = true;
                break;
            }
        }
        if (!logic) {
            if (music)
                mediaWin.start();
            for (int i = 1; i <= 3; i++) {
                if (localStorge.getInt("Score" + i) == 0 || counter < localStorge.getInt("Score" + i)) {
                    shift(i);
                    localStorge.saveRecord("Score" + i, counter);
                    i = 4;
                }
            }
            startLogic = true;
            localStorge.saveBoolean("STARTLOGIC",startLogic);
            pauseTime = SystemClock.elapsedRealtime() - chronometer.getBase();
            localStorge.saveLong("PAUSETIME", pauseTime);
            chronometer.stop();
            dialog();
        }
    }

    private void dialog() {
        View view1 = LayoutInflater.from(this).inflate(R.layout.activity_custom_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(view1)
                .create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);
        view1.findViewById(R.id.refreshButton1).setOnClickListener(v -> {
            showBtn();
            logic = true;
            setNumbersToButton();
            stepCounter.setText(String.valueOf(counter));
            if (music)
                mediaShaffle.start();
            dialog.cancel();
        });
        view1.findViewById(R.id.menuButton).setOnClickListener(v -> {
            if (music) {
                mediaClose.start();
            }
            localStorge.saveBoolean("CONTINUE",true);
            finish();
            dialog.cancel();
        });
        TextView stepsId = view1.findViewById(R.id.stepsId);
        stepsId.setText(String.valueOf(counter));
    }

    private void pauseDialog() {
        View view = LayoutInflater.from(this).inflate(R.layout.pause_dialog, null);
        AlertDialog dialog = new AlertDialog.Builder(this)
                .setCancelable(false)
                .setView(view)
                .create();
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(null);

        view.findViewById(R.id.continueBtnDialog).setOnClickListener(v -> {
            pauseTime = localStorge.getLong("PAUSETIME");
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseTime);
            chronometer.start();
            logicPause = true;
            if (music) {
                mediaOpen.start();
            }
            dialog.cancel();
        });

        view.findViewById(R.id.restartBtnDialog).setOnClickListener(v -> {
            setNumbersToButton();
            showBtn();
            if (music)
                mediaShaffle.start();
            dialog.cancel();
        });

        view.findViewById(R.id.menuBtnDialog).setOnClickListener(v -> {
            if (music) {
                mediaClose.start();
            }
            finish();
            dialog.cancel();
        });
    }

    private void shift(int index) {
        for (int i = 3; i > index; i--) {
            localStorge.saveRecord("Score" + i, localStorge.getInt("Score" + (i - 1)));
        }
    }

    private void savePositions(){
        RelativeLayout containerButtons = findViewById(R.id.containerButtons);
        int count = containerButtons.getChildCount();
        int size = (int) Math.sqrt(count);

        for (int i = 0; i < count; i++) {
            int x = i / size;
            int y = i % size;
            localStorge.saveString(("POSITIONS" + i), (String) buttons[x][y].getText());
        }
        startLogic = false;
        localStorge.saveBoolean("STARTLOGIC",startLogic);
        localStorge.saveRecord("COUNT", counter);
        localStorge.saveLong("PAUSETIME", pauseTime);
    }

    private void getPositions(){
        RelativeLayout containerButtons = findViewById(R.id.containerButtons);
        int count = containerButtons.getChildCount();
        int size = (int) Math.sqrt(count);

        buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setVisibility(View.VISIBLE);

        for (int i = 0; i < count; i++) {
            int x = i / size;
            int y = i % size;

            if (localStorge.getString("POSITIONS" + i).equals(" ")){
                emptyCoordinate.setX(x);
                emptyCoordinate.setY(y);
                buttons[emptyCoordinate.getX()][emptyCoordinate.getY()].setVisibility(View.INVISIBLE);
            }
            buttons[x][y].setText(localStorge.getString("POSITIONS" + i));
        }
        counter = localStorge.getInt("COUNT");
        pauseTime = localStorge.getLong("PAUSETIME");
        chronometer.setBase(SystemClock.elapsedRealtime() - pauseTime);
        chronometer.start();

    }


    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean("ISWIN", logic);
        outState.putBoolean("LOGICPAUSE", logicPause);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        logic = savedInstanceState.getBoolean("ISWIN");
        logicPause = savedInstanceState.getBoolean("LOGICPAUSE");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("onResume", "TTT");
        stepCounter.setText(String.valueOf(counter));
        if (!logic) {
            dialog();
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseTime);
            chronometer.stop();
        } else if (!logicPause) {
            pauseDialog();
            chronometer.setBase(SystemClock.elapsedRealtime() - pauseTime);
            chronometer.stop();
        } else {
            if(!startLogic){
                pauseTime = localStorge.getLong("PAUSETIME");
                chronometer.setBase(SystemClock.elapsedRealtime() - pauseTime);
                chronometer.start();
            }

        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("onStop", "TTT");
        if(logicPause == logic){
        pauseTime = SystemClock.elapsedRealtime() - chronometer.getBase();
        }
        localStorge.saveLong("PAUSETIME", pauseTime);
        chronometer.stop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        savePositions();
    }
}