package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.content.Intent;
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
import android.os.Handler;

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
    Bitmap over;
    Bitmap changedtaxi;
    long fps = 20; //fps
    Random r = new Random();

    CountDownGameOver count_over = new CountDownGameOver();
    CountDestroyTaxi count_destroy = new CountDestroyTaxi();
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
        changedtaxi = BitmapFactory.decodeResource(res, R.drawable.taxi_crash);
        background = BitmapFactory.decodeResource(res, R.drawable.background_margin150);
        over = BitmapFactory.decodeResource(res, R.drawable.background_overwrite);

        makeTaxi(-20);

        postInvalidate();

   }

    @Override
    public void onDraw(Canvas c) {
        //1000msに20回更新 => 50msごとに更新\
        postInvalidateDelayed(1000 / fps);


        c.drawBitmap(background, 0, 0, paint);

        //数値処理//        playerY += playerVY;
        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);
            //数値処理
            taxi.playerY += taxi.playerVY;

            //描画処理
            if(taxi.flag){
                c.drawBitmap(changedtaxi,taxi.playerX, taxi.playerY, paint);
                taxi.deletecount -= 1;
                 if(taxi.deletecount == 0){
                     taxies.remove(i);
                 }

            }else{
                c.drawBitmap(testtaxi, taxi.playerX, taxi.playerY, paint);
            }

                if (detect_over){
                    //残り0(ゲームが終わる)になったときの処理
                    Intent intent = new Intent(getContext(), ResultActivity.class);
                    intent.putExtra("RESULT", count_destroy.count_destroy);
                    getContext().startActivity(intent);

                }else{

                }

        }

        //上についたタクシーを消す
        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);

            if (taxi.playerY < 150 - testtaxi.getHeight()) {
                detect_over = count_over.touchline(taxi.lane);
                removeTaxi(taxi);
            }
        }

        //タクシーを4台まで生成する
        if (taxies.size() < 5){

            int i = new Random().nextInt(40);

            if (i == 1) {

                int speed = new java.util.Random().nextInt(30) + 5;
                int playerVY = -speed;

                makeTaxi(playerVY);
            }

        }

        //タクシーが白背景の下を通るように
        c.drawBitmap(over, 0, 0, paint);

        //書式設定
        paint.setARGB(255,0,0,0);
        paint.setTextSize(72);
        paint.setAntiAlias(true);

        //台数カウントダウン表示

        for (int i=0; i < 5; i++) {
            c.drawText("" + count_over.count_over[i], 55 + i * 142, 100, paint);
        }

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

    //配列からtaxiを消す
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
                for(int i =0; i < taxies.size(); i++){
                    Taxi taxi = taxies.get(i);
                    float taxix = taxi.playerX;
                    float taxiy = taxi.playerY;
                    float taxih = taxi.height;
                    float taxiw = taxi.width;
                    if (x >= taxix && x <= taxix + taxiw && y >= taxiy && y <= taxiy + taxih && y >150){
                        taxi.playerVY = 0  ;
                        count_destroy.increment();
                        taxi.flag = true;
                    }

                }
        }
        return false;
    }


}
