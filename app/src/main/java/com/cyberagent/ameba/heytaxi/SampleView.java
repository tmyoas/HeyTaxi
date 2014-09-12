package com.example.misa0429.myapplication;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View {
    Paint paint = new Paint();
    int playerY;  //スタートのY座標
    int playerVY = -10;  //上に10ずつ動く
    Bitmap taxi;
    int width; //タクシーの画像の幅
    int height; //タクシーの画像の高さ
    int viewWidth; //画面の幅
    int viewHeight; //画面の高さ

    //5つのレーンのX座標
    int[] lane = new int[]{0, 216, 432, 648, 846};


    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        viewWidth = getWidth();
        viewHeight = getHeight();

        playerY = viewHeight;

    }

    public SampleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SampleView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    public SampleView(Context context) {
        super(context);
        init();
    }

    private void init() {
        //画像読み込み
        Resources res = this.getContext().getResources();
        taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
        width = taxi.getWidth();
        height = taxi.getHeight();
    }

    int r = new java.util.Random ().nextInt (4);
    int playerX = lane[r];

    @Override
    public void onDraw(Canvas c) {

        //数値処理
        playerY += playerVY;

        //上まで行ったら下に戻る動き
       if(playerY < 0) {
           playerY = viewHeight;
           r = new java.util.Random ().nextInt (4);
           playerX = lane[r];
       }
        //描画処理
        c.drawBitmap (taxi, playerX, playerY, paint);




        // ループ処理、スピードの調整（ミリ秒）
            postInvalidateDelayed(50);

    }
}
