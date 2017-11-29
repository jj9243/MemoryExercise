package org.androidtown.memoryexcercise;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

public class NumRemeberQuiz extends AppCompatActivity {
    //변수
    SharedPreferences randNumPref;
    SharedPreferences roundPref;
    SharedPreferences timesPref;
    SharedPreferences scorePref;

    int randomNumber;
    int round;
    int times;
    int score;

    //editText
    EditText editText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_num_remeber_quiz);

        editText = (EditText)findViewById(R.id.editText2);
        editText.requestFocus();

        randNumPref = getSharedPreferences("randNum", MODE_PRIVATE);
        randomNumber = randNumPref.getInt("randNum",0);

        roundPref = getSharedPreferences("round",MODE_PRIVATE);
        round = roundPref.getInt("round",1);

        timesPref = getSharedPreferences("times",MODE_PRIVATE);
        times = timesPref.getInt("times",1);

        scorePref = getSharedPreferences("score",MODE_PRIVATE);
        score = scorePref.getInt("score",0);

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int actionId, KeyEvent keyEvent) {
                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    checkNumber(Integer.parseInt(editText.getText().toString()));
                    return true;
                }
                return false;
            }
        });


    }

    public void checkNumber(int number){
        if(isCorrect(number)){//맞았을 때
            if(times<3) {
                times++;

                SharedPreferences.Editor editor = timesPref.edit();
                editor.putInt("times", times);
                editor.commit();

                score++;
                SharedPreferences.Editor scoreEditor = scorePref.edit();
                scoreEditor.putInt("score", score);
                scoreEditor.commit();

                if(times==3) {
                    if(round!=3){
                        times=1;
                        SharedPreferences.Editor editor2 = timesPref.edit();
                        editor2.putInt("times", times);
                        editor2.commit();
                        round++;
                        SharedPreferences.Editor editor3 = roundPref.edit();
                        editor3.putInt("round", round);
                        editor3.commit();}
                }
            }
            Intent intent = new Intent(getApplicationContext(), Correct.class);
            startActivity(intent);
            finish();
        }
        else{//틀렸을 때
            if(times<3) {
                times++;

                SharedPreferences.Editor editor = timesPref.edit();
                editor.putInt("times", times);
                editor.commit();

                if(times==3) {
                    if(round!=3){
                        times=1;
                        SharedPreferences.Editor editor2 = timesPref.edit();
                        editor2.putInt("times", times);
                        editor2.commit();
                        round++;
                        SharedPreferences.Editor editor3 = roundPref.edit();
                        editor3.putInt("round", round);
                        editor3.commit();
                    }
                }
            }
            Intent intent = new Intent(getApplicationContext(), Incorrect.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, InputMethodManager.HIDE_IMPLICIT_ONLY);
    }

    public boolean isCorrect(int number){
        if(number==randomNumber)
            return true;
        else
            return false;
    }

}
