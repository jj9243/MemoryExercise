package org.androidtown.memoryexcercise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

public class NumRemember extends AppCompatActivity {

    //클래스
    CountDownTimer timer;

    //텍스트 뷰
    TextView scoreText;
    TextView randNumText;
    TextView timerText;


    //변수

    SharedPreferences randNumPref;
    SharedPreferences roundPref;
    SharedPreferences timesPref;
    SharedPreferences scorePref;

    int timerValue;
    int randomNumber;
    int round;
    int times;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_remember);

        //클래스
        roundPref = getSharedPreferences("round",MODE_PRIVATE);
        round = roundPref.getInt("round",1);

        timesPref = getSharedPreferences("times",MODE_PRIVATE);
        times = timesPref.getInt("times",1);

        scorePref = getSharedPreferences("score",MODE_PRIVATE);
        score = scorePref.getInt("score",0);

        RandomNumber randomNum = new RandomNumber();
        randomNumber = randomNum.getNumber(round);

        randNumPref = getSharedPreferences("randNum", MODE_PRIVATE);
        SharedPreferences.Editor editor2 = randNumPref.edit();
        editor2.putInt("randNum", randomNumber);
        editor2.commit();

        //텍스트 뷰
        scoreText = (TextView) findViewById(R.id.scoreText);
        randNumText = (TextView) findViewById(R.id.randNumText);
        timerText = (TextView) findViewById(R.id.timerText);

        if(round==3 && times==3){
            randNumText.setText("준비된 게임이 끝났습니다.\n 종료합니다");
            SharedPreferences.Editor editor3 = roundPref.edit();
            editor3.putInt("round", 1);
            editor3.commit();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            finish();
        }

        timerValue = 0;
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timerText.setText("숫자를 " + (4 - timerValue) + "초간 잘 기억해 주세요");
                timerValue++;
//                if(timerValue==3)
//                    randNumText.setText("");
            }

            @Override
            public void onFinish() {
                timer.cancel();
                timerValue = 0;
                Intent intent = new Intent(getApplicationContext(), NumRemeberQuiz.class);
                startActivity(intent);
                finish();
            }
        };

    }

    protected void onStart() {
        super.onStart();
        timer.cancel();
        timer.start();
//        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        scoreText.setText("Score : " +score);
        randNumText.setText(randomNumber+"");
    }

}