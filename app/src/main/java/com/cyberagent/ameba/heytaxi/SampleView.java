package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.media.SoundPool;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
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
    Bitmap over;
    Bitmap changedtaxi;
    Bitmap patocar;
    long fps = 20; //fps
    Random r = new Random();
    int takasa;
    CountDownGameOver count_over = new CountDownGameOver();
    CountDestroyTaxi count_destroy = new CountDestroyTaxi();
    boolean detect_over;
    //プレイヤーの初期化
    TaxiSE se = new TaxiSE(this.getContext());
    int se0 = se.taxise[0];
    int se1 = se.taxise[1];
    int ser = se.taxise[r.nextInt(2)];
    boolean fl = false;
    Pato pato = new Pato(r.nextInt(5), -35);

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
        patocar = BitmapFactory.decodeResource(res, R.drawable.pat_default);
        over = BitmapFactory.decodeResource(res, R.drawable.background_overwrite);
        takasa = testtaxi.getHeight();
        makeTaxi(-20);

        postInvalidate();

    }

    @Override
    public void onDraw(Canvas c) {

        //1000msに20回更新 => 50msごとに更新\
        postInvalidateDelayed(1000 / fps);

        c.drawBitmap(background, 0, 0, paint);


        if(fl){
            Log.d("TAG","pato_make");
            pato.playerY += pato.playerVY;
            c.drawBitmap(patocar, pato.playerX, pato.playerY, paint);
        }

        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);

            //数値処理
            taxi.playerY += taxi.playerVY;


            //        for eachに変更必要
            for (int j = i - 1; j >= 0; j--) {
                Taxi taxi1 = taxies.get(j);
                if (taxi.lane == taxi1.lane) {

                    if (taxi.playerY - taxi1.playerY < 293 + 35) {
//math 関数使う
                        if (taxi.lane > 0 && taxi.lane < 4) {

                            if (r.nextInt(2) == 0) {
                                taxi.lane++;
                                taxi.playerX += 143;
                            } else {
                                taxi.lane--;
                                taxi.playerX -= 143;
                            }
                        }

                    }
                }
            }

            //描画処理
            if (taxi.flag) {
                c.drawBitmap(changedtaxi, taxi.playerX, taxi.playerY, paint);
                taxi.deletecount -= 1;
                if (taxi.deletecount == 0) {
                    taxies.remove(i);
                }
            } else {
                c.drawBitmap(testtaxi, taxi.playerX, taxi.playerY, paint);
            }

            if (detect_over) {
                //残り0(ゲームが終わる)になったときの処理
                Intent intent = new Intent(getContext(), ResultActivity.class);
                intent.putExtra("RESULT", count_destroy.count_destroy);
                getContext().startActivity(intent);

            } else {

            }


        }

        //上についたタクシーを消す  for eachに変更必要
        for (int i = 0; i < taxies.size(); i++) {
            Taxi taxi = taxies.get(i);

            if (taxi.playerY < 150 - testtaxi.getHeight()) {
                detect_over = count_over.touchline(taxi.lane);
                removeTaxi(taxi);
            }
        }

        if (pato.playerY < 150 - 293){
            fl = false;
            pato.playerY = 1280;
            pato.lane = r.nextInt(5);
            pato.playerX = 10 + pato.lane * 142;

        }

        //タクシーを4台まで生成する
        if (taxies.size() < 5) {

            int i = new Random().nextInt(40);

            if (i == 1) {

                int speed = new java.util.Random().nextInt(30) + 5;
                int playerVY = -speed;

                makeTaxi(playerVY);
            }


        }

//        for (int j = 0; j > 0; j-- ){
//            Taxi taxi = taxies.get(j);
//            Taxi taxi1 = taxies.get(j+1);
//
//            if (taxi1.lane == taxi.lane){
//                if (taxi1.playerY - taxi.playerY < testtaxi.getHeight() + 35) {
//
//                    if (taxi1.lane > 0 && taxi1.lane < 4){
//
//                        if (r.nextInt(2) == 0) {
//                            taxi1.lane = taxi1.lane++;
//                            taxi1.playerX = taxi1.playerX + 143;
//                        } else {
//                            taxi1.lane = taxi1.lane--;
//                            taxi1.playerX = taxi1.playerX - 143;
//                        }
//                    }
//
//                }
//            }
//        }

        //タクシーが白背景の下を通るように
        c.drawBitmap(over, 0, 0, paint);

        //書式設定
        paint.setARGB(255, 0, 0, 0);
        paint.setTextSize(72);
        paint.setAntiAlias(true);

        //台数カウントダウン表示

        for (int i = 0; i < 5; i++) {
            c.drawText("" + count_over.count_over[i], 55 + i * 142, 100, paint);
        }

    }

    //Taxiの生成
    public void makeTaxi(int playerVY) {

        Taxi taxi0 = new Taxi(r.nextInt(5), playerVY);
        taxi0.height = testtaxi.getHeight();
        taxi0.width = testtaxi.getWidth();

        taxies.add(taxi0);

    }

    //配列からtaxiを消す
    public void removeTaxi(Taxi taxi) {
        taxies.remove(taxi);

    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int action = ev.getAction();
        float x = ev.getX();
        float y = ev.getY();
        switch (action & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                for (int i = 0; i < taxies.size(); i++) {
                    Taxi taxi = taxies.get(i);
                    Pato pato = new Pato(r.nextInt(5), 15);
                    float taxix = taxi.playerX;
                    float taxiy = taxi.playerY;
                    float taxih = taxi.height;
                    float taxiw = taxi.width;
                    if (x >= taxix && x <= taxix + taxiw && y >= taxiy && y <= taxiy + taxih && y > 150) {
                        taxi.playerVY = 0;
                        count_destroy.increment();
                        taxi.flag = true;
//                        se.playSe(r.nextInt(4));
                        if(r.nextInt(1) == 0){
                            Log.d("TAG","TOUCH!");
                            fl = true ;
                        }
                    }

                }
        }
        return false;
    }


}
