package info.shadmanahmed.eggtimer;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView timerTime;
    Button goButton;
    SeekBar mySeekBar;
    Boolean isCounterActive = false;
    CountDownTimer myCountDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mySeekBar = (SeekBar) findViewById(R.id.seekBarID);
        timerTime = (TextView) findViewById(R.id.textView2);
        goButton = (Button) findViewById(R.id.buttonID);


        mySeekBar.setMax(600);
        mySeekBar.setProgress(30);
        mySeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                updateTimer(i);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    };
    public void controllerButton(View view){
        if (isCounterActive == false) {
            isCounterActive = true;

            myCountDownTimer = new CountDownTimer(mySeekBar.getProgress() * 1000 + 100, 1000) {
                @Override
                public void onTick(long l) {
                    updateTimer((int) l / 1000);
                }

                @Override
                public void onFinish() {
                    MediaPlayer myPlayer = MediaPlayer.create(getApplicationContext(), R.raw.airhorn);
                    myPlayer.start();
                    timerTime.setText("0:30");
                    mySeekBar.setProgress(30);
                    goButton.setText("Go!");
                    mySeekBar.setEnabled(true);
                    isCounterActive = false;
                    myCountDownTimer.cancel();
                }
            }.start();
            goButton.setText("Stop");
            mySeekBar.setEnabled(false);
        }
        else {
            timerTime.setText("0:30");
            mySeekBar.setProgress(30);
            isCounterActive = false;
            myCountDownTimer.cancel();
            goButton.setText("Go!");
            mySeekBar.setEnabled(true);
        }
    }

    public void updateTimer (int tickTime){
        int minutes = (int) tickTime/ 60;
        int seconds = tickTime - (minutes * 60);
        String timeString = Integer.toString(seconds);
        if (seconds < 10){
            timeString = "0" + timeString;
        }
        timerTime.setText(Integer.toString(minutes) + ":" + timeString );
    }

}
