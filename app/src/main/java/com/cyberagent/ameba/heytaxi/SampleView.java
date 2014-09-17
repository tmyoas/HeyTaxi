package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.view.View;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by misa0429 on 2014/09/11.
 */
class SampleView extends View {
    Paint paint = new Paint();
    int playerY;  //スタートのY座標
    int dispX = 720; //画面幅
    int dispY = 1280;  //画面高さ
    Resources res;
    Bitmap testtaxi;
    Bitmap background;
    long fps = 20; //fps
    Random r = new Random();

    CountDownGameOver count_over = new CountDownGameOver();
    boolean detect_over;

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
        background = BitmapFactory.decodeResource(res, R.drawable.background_margin150);

        postInvalidate();

   }

    @Override
    public void onDraw(Canvas c) {
        c.drawBitmap(background, 0, 0, paint);

        //数値処理//        playerY += playerVY;
        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);
            //数値処理
            taxi.playerY += taxi.playerVY;

            //描画処理
            c.drawBitmap(testtaxi, taxi.playerX, taxi.playerY, paint);

            //死亡判定(仮)
            if (taxi.playerY < 300) {


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

        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);

            if (taxi.playerY < 150 - testtaxi.getHeight()) {
                detect_over = count_over.touchline(taxi.lane);
                removeTaxi(taxi);
            }
        }

        if (taxies.size() < 4){

            int i = new Random().nextInt(40);

            if (i == 1) {

                int speed = new java.util.Random().nextInt(30) + 5;
                int playerVY = -speed;


                makeTaxi(playerVY);
            }

        }

        paint.setARGB(255,0,0,0);
        paint.setTextSize(72);
        paint.setAntiAlias(true);
        c.drawText("" + count_over.count_over[0], 55 + 0 * 142,100,paint);
        c.drawText("" + count_over.count_over[1], 55 + 1 * 142,100,paint);
        c.drawText("" + count_over.count_over[2], 55 + 2 * 142,100,paint);
        c.drawText("" + count_over.count_over[3], 55 + 3 * 142,100,paint);
        c.drawText("" + count_over.count_over[4], 55 + 4 * 142,100,paint);


        //1000msに20回更新 => 50msごとに更新
        postInvalidateDelayed(1000 / fps);



    }

    //Taxiの生成
    public void makeTaxi(int playerVY){

        Taxi taxi0 = new Taxi(r.nextInt(5), playerVY);
        taxi0.height = testtaxi.getHeight();
        taxi0.width = testtaxi.getWidth();

        taxies.add(taxi0);

        }

    public void removeTaxi (Taxi taxi){
        taxies.remove(taxi);


    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x = ev.getX();
        float y = ev.getY();
        switch (action& MotionEvent.ACTION_MASK ){
            case MotionEvent.ACTION_DOWN:
                Log.d("TAG", "action : 2 : ");//動作確認(済)
                for(int i =0; i < taxies.size(); i++){
                    Taxi taxi = taxies.get(i);
                    float taxix = taxi.playerX;
                    float taxiy = taxi.playerY;
                    float taxih = taxi.height;
                    float taxiw = taxi.width;
                    if (x >= taxix && x <= taxix + taxiw && y >= taxiy && y <= taxiy + taxih){
                        Log.d("TAG", "action : 3 : " );
                        taxies.remove(i);
                    }
                }
        }
        return false;
    }

}
