package org.androidtown.memoryexcercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.inputmethod.InputMethodManager;

public class Incorrect extends AppCompatActivity {

    CountDownTimer timer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_incorrect);

        timer = new CountDownTimer(2000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
            }

            @Override
            public void onFinish() {
                timer.cancel();
                Intent intent = new Intent(getApplicationContext(), NumRemember.class);
                startActivity(intent);
                finish();
            }
        };
        startActivity(new Intent(this, NumRemember.class));
    }

    @Override
    protected void onStart() {
        super.onStart();
        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
        timer.cancel();
        timer.start();
    }

//    @Override
//    protected void onStop() {
//        super.onStop();
//        InputMethodManager immhide = (InputMethodManager) getSystemService(Activity.INPUT_METHOD_SERVICE);
//        immhide.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
//    }
}
