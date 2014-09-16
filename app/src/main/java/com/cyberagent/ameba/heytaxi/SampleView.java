package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View {
    Paint paint = new Paint();
    int dispX = 1080; //画面幅
    int dispY = 1920;  //画面高さ

    Resources res;
//    int playerX; //タクシー位置x方向
    int playerY; //タクシー位置y方向
    int playerVY = -10;  //上に10ずつ動く

    Bitmap testtaxi;
    long fps = 20; //fps

    CountDownGameOver count_over = new CountDownGameOver();
    boolean detect_over;

    //5つのレーンのX座標
//    int[] lane = new int[5];
    int[]lane = new int[]{0, dispX / 5, 2 * dispX / 5, 3 * dispX / 5, 4 * dispX / 5};

    //resをTaxiに渡そうとすると問題を起こしてアプリが終了します なんでだ
//    Taxi taxi = new Taxi(res, lane[r], playerVY);
    Taxi taxi0 = new Taxi(lane[new Random().nextInt(5)], playerVY);

    // タクシーを作る(&消す)メソッドもしくはクラスが必要
//    Taxi taxi1 = new Taxi(lane[new Random().nextInt(5)], playerVY-2);
//    Taxi taxi2 = new Taxi(lane[new Random().nextInt(5)], playerVY-4);
//    Taxi taxi3 = new Taxi(lane[new Random().nextInt(5)], playerVY-6);
//    Taxi taxi4 = new Taxi(lane[new Random().nextInt(5)], playerVY-8);

    //複数のタクシー管理
    ArrayList<Taxi> taxies = new ArrayList<Taxi>();

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
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
        res = this.getContext().getResources();
        testtaxi = BitmapFactory.decodeResource(res, R.drawable.taxi_default);
//        width = taxi.getWidth();
//        height = taxi.getHeight();

        taxies.add(taxi0);
//        taxies.add(taxi1);
//        taxies.add(taxi2);
//        taxies.add(taxi3);
//        taxies.add(taxi4);
    }

    @Override
    public void onDraw(Canvas c) {

        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);
            //数値処理
            taxi.playerY += taxi.playerVY;

            //上まで行ったら下に戻る動き
//        if(playerY < 0) {
//            playerY = dispY;
//            r = new java.util.Random ().nextInt (5);
//            playerX = lane[r];
//        }
            //描画処理
            c.drawBitmap(testtaxi, taxi.playerX, taxi.playerY, paint);

        }

        // ループ処理、スピードの調整（ミリ秒）
        //1000msに20回更新 => 50msごとに更新
        postInvalidateDelayed(1000 / fps);

    }

    //Taxiの生成(未完成)
    public void makeTaxi(){

    }
}
