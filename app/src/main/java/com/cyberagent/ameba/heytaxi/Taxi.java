package com.cyberagent.ameba.heytaxi;


import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Paint;

//abstract class Taxi {

    class Taxi {

    Paint paint = new Paint();
    //スタートのY座標    int playerVY = -10;  //上に10ずつ動く
    int playerX; //スタートのx座標
    int playerY = 1280;  //スタートのY座標
    int playerVY;  //y方向移動量
    Bitmap taxi;
    Resources res;
    int width; //タクシーの画像の幅
    int height; //タクシーの画像の高さ
    int viewWidth; //画面の幅
    int viewHeight; //画面の高さ
    int dispX = 1080; //画面幅
    int lane ;

        //コンストラクタ
        Taxi(Resources res, int playerX, int playerVY ){
            this.taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
            this.width = taxi.getWidth();
            this.height = taxi.getHeight();
            this.playerX = playerX;
            this.playerVY = playerVY;
        }


            //画像読み込みfrom SampleView  書き換え必要(constructor)
//        private void init () {

//        Resources res = this.getContext().getResources();
//            taxi = BitmapFactory.decodeResource(res, R.drawable.ic_launcher);
//            width = taxi.getWidth();
//            height = taxi.getHeight();
//        }
       Taxi( int lane, int playerVY){
            this.lane = lane;
            this.playerX = 10 + lane * 142;
            this.playerVY = playerVY;
        }

        //    CollisionDetection(Resources res, int playerX, int playerVY) {
    //        super(res, playerX, playerVY);
    //        ImageView imageView = (ImageView)
    //        imageView.setImageBitmap(taxi);
    //    }
//    public abstract boolean OnTouchEvent(MotionEvent ev);


//    int r = new java.util.Random().nextInt(4);
//        int playerX = lane[r];

//    @Override
//    public void onDraw(Canvas c) {
//
//        数値処理
//        playerY += playerVY;
/*
        //上まで行ったら下に戻る動き
        if (playerY < 0) {
            playerY = viewHeight;
            r = new java.util.Random().nextInt(4);
            playerX = lane[r];
        }
        //描画処理
        c.drawBitmap(taxi, playerX, playerY, paint);
//

        // ループ処理、スピードの調整（ミリ秒）
        postInvalidateDelayed(50);

    }
*/
        //以下タッチイベントですがエラーです
//        @Override
//        public boolean onTouchEvent (MotionEvent ev){
//            float x = ev.getX();
//            float y = ev.getY();
//            String action = "";
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

//        return true;}
    }
