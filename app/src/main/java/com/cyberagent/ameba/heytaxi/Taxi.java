package com.cyberagent.ameba.heytaxi;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

class Taxi {
    Paint paint = new Paint();
    int playerX; //スタートのx座標
    int playerY = 1920;  //スタートのY座標
    int playerVY;  //y方向移動量
    Bitmap taxi;
    int width; //タクシーの画像の幅
    int height; //タクシーの画像の高さ
    int viewWidth; //画面の幅
    int viewHeight; //画面の高さ

    //コンストラクタ
    Taxi (Resources res, int playerX, int playerVY) {
        this.taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
        this.width = taxi.getWidth();
        this.height = taxi.getHeight();
        this.playerX = playerX;
        this.playerVY = playerVY;
    }

    Taxi (int playerX, int playerVY) {
        this.playerX = playerX;
        this.playerVY = playerVY;
    }

    //以下タッチイベントですがエラーです
////    @Override
//    public boolean TouchEvent(MotionEvent ev) {
//        float x = ev.getX();
//        float y = ev.getY();
//        String action = "";
//
//        if (viewWidth / 2 - width / 2 <= x && x <= viewWidth / 2 + width / 2 && playerY <= y && y <= playerY + viewHeight) {
//            switch (ev.getAction() & MotionEvent.ACTION_MASK) {
//                case MotionEvent.ACTION_DOWN:
//                    action = "Touch Down";
//                  //  touch_count += 1;
////                    taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher_reverse);
//                    playerVY = 0;
//
//                    //playerVY = -1920;
//                    break;
//                case MotionEvent.ACTION_MOVE:
//                    action = "Touch Move";
//                    break;
//                case MotionEvent.ACTION_UP:
//                    action = "Touch Up";
////                    taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
//                    break;
//                case MotionEvent.ACTION_CANCEL:
//                    action = "Touch Cancel";
//                    break;
//            }
//           // Log.d("TAG", "action : " + action + " count : " + touch_count);
//        } else {
//        }
//        return true;
//    }


}