package com.cyberagent.ameba.heytaxi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Paint;

/**
 * Created by Yamada on 2014/09/17.
 */
public class Pato {

    Paint paint = new Paint();
    int playerX; //スタートのx座標
    int playerY = 1280;  //スタートのY座標
    int playerVY;  //y方向移動量
    Bitmap pato;
    Resources res;
    int width;
    int height;
    int viewWidth; //画面の幅
    int viewHeight; //画面の高さ
    int dispX = 1080; //画面幅
    int lane ;
    boolean flag = false;

    //コンストラクタ
    Pato( int lane, int playerVY){
        this.lane = lane;
        this.playerX = 10 + lane * 142;
        this.playerVY = playerVY;
    }

}

