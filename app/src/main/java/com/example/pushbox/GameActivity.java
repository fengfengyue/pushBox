package com.example.pushbox;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;
import android.widget.Toolbar;

import android.view.View;

import java.util.ArrayList;

public class GameActivity extends Activity {

    ArrayList<int[][]> arrayList = new ArrayList<>();
    Button up,down,left,right,back,previous,next;
    View_pushBox myImageView_circle;
    Toolbar mToolbar;

    LevelDBHelper mLevelDBHelper = new LevelDBHelper(this);
    int LV = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        mLevelDBHelper.insert("a",LV+"");
        LV_data lv_data = new LV_data();
        arrayList = lv_data.arrayList;

        myImageView_circle = (View_pushBox)findViewById(R.id.nnn);
        myImageView_circle.setPassListener(new View_pushBox.PassListener() {
            @Override
            public void pass() {
                final AlertDialog.Builder normalDialog = new AlertDialog.Builder(GameActivity.this);
                normalDialog.setTitle("恭喜你").setMessage("真厉害，你已通过本关！")
                        .setPositiveButton("下一关",
                                new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        if(LV<arrayList.size()){
                                            LV = LV + 1;
                                            mLevelDBHelper.update("a",LV+"");
                                            myImageView_circle.setDesign(arrayList.get(LV-1));
                                        }else{
                                            Toast.makeText(getApplicationContext(),"已经是最后一关",Toast.LENGTH_LONG).show();
                                        }
                                    }
                                }
                        ).show();
            }
        });



        up = (Button)findViewById(R.id.up);
        down = (Button)findViewById(R.id.down);
        left = (Button)findViewById(R.id.left);
        right = (Button)findViewById(R.id.right);
        back = (Button) findViewById(R.id.back);
        previous = (Button)findViewById(R.id.previous);
        next = (Button)findViewById(R.id.next);

        up.setOnClickListener(onClick);
        down.setOnClickListener(onClick);
        left.setOnClickListener(onClick);
        right.setOnClickListener(onClick);
        back.setOnClickListener(onClick);
        previous.setOnClickListener(onClick);
        next.setOnClickListener(onClick);

        mLevelDBHelper.clean();

    }

    View.OnClickListener onClick = new View.OnClickListener(){
        public void onClick(View v){
            switch (v.getId()){
                case R.id.up:
                    myImageView_circle.moveUp();
                    break;
                case R.id.down:
                    myImageView_circle.moveDown();
                    break;
                case R.id.left:
                    myImageView_circle.moveLeft();
                    break;
                case R.id.right:
                    myImageView_circle.moveRight();
                    break;
                case R.id.back:
                    myImageView_circle.moveBack();
                    break;
                case R.id.previous:
                    if(LV>1){
                        LV = LV -1;
                        mLevelDBHelper.update("a",LV+"");
                        myImageView_circle.setDesign(arrayList.get(LV-1));
                    }
                    break;
                case R.id.next:
                    if(LV<arrayList.size()){
                        LV = LV +1;
                        mLevelDBHelper.update("a",LV+"");
                        myImageView_circle.setDesign(arrayList.get(LV-1));
                    }else{
                        Toast.makeText(getApplicationContext(),"已是最后一关",Toast.LENGTH_LONG).show();
                    }
                    break;
            }
        }
    };


}
