package com.example.pushbox;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;

public class SelectActivity extends Activity {

    //自动跳转形式，没有关卡的选择了，这个activity暂时不用，放在这里如果有需求要用的话再说

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select);

        Button btnOne = (Button)findViewById(R.id.btnOne);
        Button btnTwo = (Button)findViewById(R.id.btnTwo);
        Button btnThree = (Button)findViewById(R.id.btnThree);

        btnOne.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SelectActivity.this,GameActivity.class);
                startActivity(intent);
            }
        });

    }

}
