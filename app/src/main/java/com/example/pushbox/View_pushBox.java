package com.example.pushbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

import java.util.ArrayList;

public class View_pushBox extends ImageView {
    ArrayList<int[][]> arrayList = new ArrayList<>();//用一个arrayList，其中每一个元素是一个布局界面也就是一个int数组
    // arrayList就是一个关卡中所有界面的集合，也就是通过arrayList来实现小人，箱子的移动
    int[][][] stepArg = new int[300][15][15];//用来存每一步的view
    int step = 0;//代表步数
    Canvas canvas;//首先定义一个画布
    private int mWidth;
    private int mHeight;//定义画布的宽和高
    Boolean flag=false;//用此标记来确定是否通过本关卡，如果等于ture则转到下一关的界面
    //Bitmap是表示一张图片，在这里是用资源文件来获取图片
    Bitmap bitmap0 = BitmapFactory.decodeResource(getResources(),R.mipmap.wall);//墙
    Bitmap bitmap1 = BitmapFactory.decodeResource(getResources(),R.mipmap.way);//路
    Bitmap bitmap2 = BitmapFactory.decodeResource(getResources(),R.mipmap.person);//人
    Bitmap bitmap3 = BitmapFactory.decodeResource(getResources(),R.mipmap.emptybox);//空箱子
    Bitmap bitmap4 = BitmapFactory.decodeResource(getResources(),R.mipmap.box);//满箱子
    Bitmap bitmap5 = BitmapFactory.decodeResource(getResources(),R.mipmap.homeaddress);//目的地
    Bitmap bitmap6 = BitmapFactory.decodeResource(getResources(),R.mipmap.personaddress);//人到目的地
    int [][]design = {//这其中的012345分别代表上面的bitmap0-5
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,5,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,1,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,3,1,3,5,0,0,0,0,0},
            {0,0,0,0,5,3,2,1,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,3,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,5,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0},
            {0,0,0,0,0,0,0,0,0,0,0,0,0,0,0}
    };//这个是点开这个页面的初始页面，也就是第一关的布局

    public interface PassListener{
        void pass();
    }//这个函数当作一个监听器来处理，在这里是一个接口，
    // 具体的实现在GameActivity中实现，这个pass函数就是来处理通过关之后的操作

    private PassListener mPassListener;

    public void setPassListener(PassListener mPassListener){
        this.mPassListener = mPassListener;
    }//设置一个监听器。。。

    //下面是对这个函数的初始化过程
    public View_pushBox(Context context) {
        super(context);
        init("MyImageView(Context context)");
    }

    public View_pushBox(Context context, AttributeSet attrs){
        super(context , attrs);
        init("MyImageView(Context context,AttributeSet attrs)");
    }

    public View_pushBox(Context context,AttributeSet attrs,int defstyle){
        super(context, attrs,defstyle);
        init("MyImageView(Context context,AttributeSet attrs,int defstyle)");
    }

    private void init(String structName) {
    }//初始化类型的标注（以后写初始化最好都写一个这个hh）

    protected void onMeasure(int widthMeasureSpec , int heightMeasureSpec){
        //根据父容器传递跟子容器的大小要求来确定子容器的大小
        int specSize = MeasureSpec.getSize(widthMeasureSpec);
        //得到父容器传过来的宽和高
        mWidth = specSize;
        specSize = MeasureSpec.getSize(heightMeasureSpec);
        mHeight = specSize;
        Log.e("宽高：","宽"+mWidth+"高"+mHeight);
        setMeasuredDimension(mWidth,mHeight);
        //设置此view的宽和高
    }

    protected void onDraw(Canvas canvas){//对画布进行绘图
        Log.e("onDraw","onDraw");
        this.canvas = canvas;
        super.onDraw(canvas);
        Paint p = new Paint(); //设置画布
        //p.setColor(Color.RED); //画布颜色
        for (int i=0;i<15;i++){
            for (int k =0; k<15; k++){
                int x = design[i][k];
                Rect rect = new Rect((mWidth/15)*k,(mWidth/15)*i,(mWidth/15)*(k+1),
                        (mWidth/15)*(i+1));
                //产生一个矩形，这个函数的四个变量分别是左边界右边界上边界下边界
                switch (x){
                    case 0:
                        canvas.drawBitmap(bitmap0,null,rect,p);
                        break;
                    case 1:
                        canvas.drawBitmap(bitmap1,null,rect,p);
                        break;
                    case 2:
                        canvas.drawBitmap(bitmap2,null,rect,p);
                        break;
                    case 3:
                        canvas.drawBitmap(bitmap3,null,rect,p);
                        break;
                    case 4:
                        canvas.drawBitmap(bitmap4,null,rect,p);
                        break;
                    case 5:
                        canvas.drawBitmap(bitmap5,null,rect,p);
                        break;
                    case 6:
                        canvas.drawBitmap(bitmap6,null,rect,p);
                        break;
                }
            }
        }
    }

    public void setDesign(int [][]xxx){ //对页面重新设计，用于跳转上一关下一关
        for (int i=0;i<15;i++){
            for (int k=0; k<15 ; k++){
                design[i][k] = xxx[i][k];
            }
        }
        step = 0;//步数初始化
        arrayList.clear();
        postInvalidate();   //对view页面进行刷新
    }

    public void moveLeft(){
        addBack();
        flag = false;
        for (int i=0;i<15;i++){
            if (flag){
                break;
            }
            for (int k=0;k<15;k++){
                if (flag){
                    break;
                }
                int x = design[i][k];
                if (x==2|x==6){
                    Log.e("x","i:"+i+"k:"+k);
                    switch (design[i][k-1]){
                        case 0:
                            break;
                        case 1:
                            if (x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i][k-1] = 2;
                            break;
                        case 3:
                            if(design[i][k-2]==5){
                                design[i][k-2] = 4;
                                design[i][k-1] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if(design[i][k-2]==1){
                                design[i][k-2] = 3;
                                design[i][k-1] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 4:
                            if(design[i][k-2]==5){
                                design[i][k-2] = 4;
                                design[i][k-1] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if(design[i][k-2]==1){
                                design[i][k-2] = 3;
                                design[i][k-1] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 5:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i][k-1] = 6;
                            break;
                    }
                    flag = true;
                }
            }
        }
        postInvalidate();
        ifPass();
    }

    public void moveRight(){
        addBack();
        flag = false;
        for(int i=0;i<15;i++){
            if(flag){
                break;
            }
            for(int k=0;k<15;k++){
                int x = design[i][k];
                if(flag){
                    break;
                }
                if(x==2|x==6){
                    Log.e("x",""+x+"i:"+i+"k:"+k);
                    switch (design[i][k+1]){
                        case 0:
                            break;
                        case 1:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i][k+1] = 2;
                            break;
                        case 3:
                            if(design[i][k+2]==5){
                                design[i][k+2] = 4;
                                design[i][k+1] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i][k+2]==1){
                                design[i][k+2] = 3;
                                design[i][k+1] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 4:
                            if(design[i][k+2]==5){
                                design[i][k+2] = 4;
                                design[i][k+1] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i][k+2]==1){
                                design[i][k+2] = 3;
                                design[i][k+1] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 5:
                            if(x==6){design[i][k] = 5;}else{
                                design[i][k] = 1;
                            }
                            design[i][k+1] = 6;
                            break;
                    }
                    flag = true;
                }
            }
        }
        postInvalidate();
        ifPass();
    }
    public void moveUp(){
        addBack();
        flag = false;
        for(int i=0;i<15;i++){
            if(flag){
                break;
            }
            for(int k=0;k<15;k++){
                int x = design[i][k];
                if(flag){
                    break;
                }
                if(x==2|x==6){
                    Log.e("x",""+x+"i:"+i+"k:"+k);
                    switch (design[i-1][k]){
                        case 0:
                            break;
                        case 1:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i-1][k] = 2;
                            break;
                        case 3:
                            if(design[i-2][k]==5){
                                design[i-2][k] = 4;
                                design[i-1][k] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i-2][k]==1){
                                design[i-2][k] = 3;
                                design[i-1][k] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 4:
                            if(design[i-2][k]==5){
                                design[i-2][k] = 4;
                                design[i-1][k] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i-2][k]==1){
                                design[i-2][k] = 3;
                                design[i-1][k] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 5:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i-1][k] = 6;
                            break;
                    }
                    flag = true;
                }
            }
        }
        postInvalidate();
        ifPass();
    }
    public void moveDown(){
        addBack();
        flag = false;
        for(int i=0;i<15;i++){
            if(flag){
                break;
            }
            for(int k=0;k<15;k++){
                int x = design[i][k];
                if(flag){
                    break;
                }
                if(x==2|x==6){
                    Log.e("x",""+x+"i:"+i+"k:"+k);
                    switch (design[i+1][k]){
                        case 0:
                            break;
                        case 1:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i+1][k] = 2;
                            break;
                        case 3:
                            if(design[i+2][k]==5){
                                design[i+2][k] = 4;
                                design[i+1][k] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i+2][k]==1){
                                design[i+2][k] = 3;
                                design[i+1][k] = 2;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 4:
                            if(design[i+2][k]==5){
                                design[i+2][k] = 4;
                                design[i+1][k] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else if (design[i+2][k]==1){
                                design[i+2][k] = 3;
                                design[i+1][k] = 6;
                                if(x==6){
                                    design[i][k] = 5;
                                }else{
                                    design[i][k] = 1;
                                }
                            }else{}
                            break;
                        case 5:
                            if(x==6){
                                design[i][k] = 5;
                            }else{
                                design[i][k] = 1;
                            }
                            design[i+1][k] = 6;
                            break;
                    }
                    flag = true;
                }
            }
        }
        postInvalidate();
        ifPass();
    }

    public void ifPass(){
        flag = false;
        for (int i=0;i<15;i++){
            if(flag){
                break;
            }
            for (int k=0;k<15;k++){
                int x = design[i][k];
                if(flag){
                    break;
                }
                if(x==3){
                    flag = true;
                }
            }
        }
        if(!flag){
            mPassListener.pass();
        }
    }

    public void moveBack(){
        if(arrayList.size()>0){
            this.design = (arrayList.get(arrayList.size()-1));
            postInvalidate();
            arrayList.remove(arrayList.size()-1);
        }
    }

    public void addBack(){
        if(step>299){
            arrayList.clear();
            step = 0;
        }
        if(arrayList.size()>99){
            arrayList.remove(0);
            //防止过多步导致溢出，用这种方法当满100的时候把第一个数组移除保证数组不浪费空间
        }
        for (int i=0;i<15;i++){
            for (int k=0;k<15;k++){
                stepArg[step][i][k] = design[i][k];
            }
        }
        arrayList.add(stepArg[step]);
        step++;
    }

}
