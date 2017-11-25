package org.androidtown.memoryexcercise;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    //버튼 UI
    Button enterEx1Btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //버튼 UI 선언
        enterEx1Btn = (Button) findViewById(R.id.enterEx1Btn);

        //버튼을 클릭할 경우
        enterEx1Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Excercise1Activity.class);
                startActivity(intent);
            }
        });
    }
}