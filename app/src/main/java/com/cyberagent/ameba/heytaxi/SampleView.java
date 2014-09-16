package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
<<<<<<< HEAD
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
=======
import android.view.View;

>>>>>>> 2446005eb03fde0749f6759a0e489682703cfd0b
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View {
    Paint paint = new Paint();
    int playerY;  //スタートのY座標    int dispX = 1080; //画面幅    int dispY = 1920;  //画面高さ
    int dispX = 720; //画面幅
    int dispY = 1280;  //画面高さ

    Resources res;
<<<<<<< HEAD
//    int playerX; //タクシー位置x方向//    int playerY; //タクシー位置y方向    int playerX; //タクシー位置x方向
//    int playerY; //タクシー位置y方向
=======
    int playerY; //タクシー位置y方向
>>>>>>> 2446005eb03fde0749f6759a0e489682703cfd0b

    int playerVY = -10;  //上に10ずつ動く
    Bitmap testtaxi;
    Bitmap background;
    long fps = 20; //fps

<<<<<<< HEAD
    //5つのレーンのX座標
//    int[]lane = new int[]{0, 216, 432, 648, 846};//    int[] lane = new int[5];
    int[]lane = new int[]{0, dispX / 5, 2 * dispX / 5, 3 * dispX / 5, 4 * dispX / 5};
=======
    CountDownGameOver count_over = new CountDownGameOver();
    boolean detect_over;
>>>>>>> 2446005eb03fde0749f6759a0e489682703cfd0b

    //5つのレーンのX座標

    //resをTaxiに渡そうとすると問題を起こしてアプリが終了します なんでだ
//    Taxi taxi = new Taxi(res, lane[r], playerVY);
    Taxi taxi0 = new Taxi(lane[new Random().nextInt(5)], playerVY) ;
//    @Override//    public void onWindowFocusChanged(boolean hasWindowFocus) {//        super.onWindowFocusChanged(hasWindowFocus);//        viewWidth = getWidth();//        viewHeight = getHeight();    // タクシーを作る(&消す)メソッドもしくはクラスが必要
//    Taxi taxi1 = new Taxi(lane[new Random().nextInt(5)], playerVY-2);
//    Taxi taxi2 = new Taxi(lane[new Random().nextInt(5)], playerVY-4);
//    Taxi taxi3 = new Taxi(lane[new Random().nextInt(5)], playerVY-6);
//    Taxi taxi4 = new Taxi(lane[new Random().nextInt(5)], playerVY-8);

//        playerY = viewHeight;    //複数のタクシー管理
    ArrayList<Taxi> taxies = new ArrayList<Taxi>();

//    }    @Override
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

//    private void init() {
        //画像読み込み
//        Resources res = this.getContext().getResources();
//        taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
//    public SampleView(Context context) {
//        super(context);
//        init();
//    }

    private void init() {
        //画像読み込み

        res = this.getContext().getResources();
        testtaxi = BitmapFactory.decodeResource(res, R.drawable.taxi_default);
        background = BitmapFactory.decodeResource(res, R.drawable.background);

        taxies.add(taxi0);
        //        taxies.add(taxi1);
        //        taxies.add(taxi2);
        //        taxies.add(taxi3);
        //        taxies.add(taxi4);
            int speed = new java.util.Random().nextInt(40);
            playerVY = - speed;

        while (taxies.size() < 4) {
            makeTaxi(playerVY);
        }

    }

<<<<<<< HEAD
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        int x = (int)ev.getX();
        int y = (int)ev.getY();
        switch (action & MotionEvent.ACTION_MASK){
            case MotionEvent.ACTION_DOWN:
                int i = 0;
                for(; i < taxies.size(); i++){
                    Taxi taxi = taxies.get(i);
                    int taxix = taxi.playerX;
                    int taxiy = taxi.playerY;
                    int taxih = taxi.height;
                    int taxiw = taxi.width;
                    if (x >= taxix && x <= taxix + taxiw && y >= taxiy && y <= taxiy + taxih);{
                        taxies.remove(i);
                    }
                }
        }
        return false;
    }


//    int r = new java.util.Random ().nextInt (5);
//    int playerX = lane[r];
=======
>>>>>>> 2446005eb03fde0749f6759a0e489682703cfd0b

    @Override
    public void onDraw(Canvas c) {
        c.drawBitmap(background, 0, 0, paint);

        //数値処理//        playerY += playerVY;
        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);
            //数値処理
            taxi.playerY += taxi.playerVY;


            //上まで行ったら下に戻る動き
//        if(playerY < 0) {
//            playerY = dispY;
//            r = new java.util.Random ().nextInt (5);//            playerX = lane[r];//            playerX = lane[new Random().nextInt(5)];
//        }
            //描画処理
            c.drawBitmap(testtaxi, taxi.playerX, taxi.playerY, paint);

            //死亡判定(仮)
            if (taxi.playerY < 300) {

                detect_over = count_over.touchline(taxi.lane);
//                Log.v("CHECK", "count_over[i] : " + count_over.count_over[i]);
                if (detect_over){
                    //残り0(ゲームが終わる)になったときの処理
//                    Log.v("CHECK", "GameEnd");
                }else{
                    //それ以外(まだゲームが続く)のときの処理
//                    Log.v("CHECK", "Continue");
                }
            }
        }

        // ループ処理、スピードの調整（ミリ秒）
//            postInvalidateDelayed(50);
        //1000msに20回更新 => 50msごとに更新
        postInvalidateDelayed(1000 / fps);

    }

    }

<<<<<<< HEAD
    //Taxiの生成(未完成)    public void makeTaxi(){

=======
>>>>>>> 2446005eb03fde0749f6759a0e489682703cfd0b
    //Taxiの生成
    public void makeTaxi(int playerVY){

        int r = new Random().nextInt(5);
        Taxi taxi0 = new Taxi(r, playerVY) ;

        taxies.add(taxi0);

        }

}
