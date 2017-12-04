package org.androidtown.memoryexcercise;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //버튼 UI
    Button enterEx1Btn, enterEx2Btn, enterEx3Btn, enterEx4Btn;

    //클래스
    NumRemember numRemember;
    NumRemeberQuiz numRemeberQuiz;

    /// /버튼
    Button numRememberButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //클래스
        numRemember = new NumRemember();
        numRemeberQuiz = new NumRemeberQuiz();

        //클래스
        SharedPreferences roundPref = getSharedPreferences("round",MODE_PRIVATE);
        SharedPreferences timesPref = getSharedPreferences("times",MODE_PRIVATE);
        SharedPreferences randNumPref = getSharedPreferences("randNum", MODE_PRIVATE);

        SharedPreferences.Editor editor = randNumPref.edit();
        editor.putInt("randNum", 0);
        editor.commit();

        editor = timesPref.edit();
        editor.putInt("times", 1);
        editor.commit();

        editor = roundPref.edit();
        editor.putInt("round", 1);
        editor.commit();

        //버튼 UI 선언
        enterEx1Btn = (Button) findViewById(R.id.enterEx1Btn);
        enterEx2Btn = (Button) findViewById(R.id.enterEx2Btn);
        enterEx3Btn = (Button) findViewById(R.id.enterEx3Btn);
        enterEx4Btn = (Button)findViewById(R.id.enterEx4Btn);

        //버튼을 클릭할 경우
        enterEx1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Excercise1Activity.class);
                startActivity(intent);
            }
        });

        enterEx2Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Game2Activity.class);
                startActivity(intent);
            }
        });

        enterEx3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), NumRemember.class);
                startActivity(intent);
            }
        });
        enterEx4Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), excercise4Activity.class);
                startActivity(intent);
            }
        });
    }
}