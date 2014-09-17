package com.cyberagent.ameba.heytaxi;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Random;

import static android.view.View.*;

/**
 * Created by Yamada on 2014/09/15.
 */
public class CollisionDetection extends Taxi  {
    int playerX;
    int playerY;
    int playerVY;
    Bitmap taxi;
    ArrayList<Taxi> taxies = new ArrayList<Taxi>();
    int dispX = 1080; //画面幅
    int[] lane = new int[]{0, dispX / 5, 2 * dispX / 5, 3 * dispX / 5, 4 * dispX / 5};
    Taxi taxi0 = new Taxi(lane[new Random().nextInt(5)], playerVY) {
        //        @Override
        public boolean OnTouchEvent(MotionEvent ev) {
            return false;
        }
    };

    CollisionDetection(int lane, int playerVY) {
        super(lane, playerVY);
    }

    private void init() {
        taxies.add(taxi0);
    }
}
//



//    CollisionDetection(Resources res, int playerX, int playerVY) {
//        super(res, playerX, playerVY);
//        ImageView imageView = (ImageView)
//        imageView.setImageBitmap(taxi);
//    }
//@Override
//    public boolean OnTouchEvent(MotionEvent ev) {
//        int action = ev.getAction();
//        int x = (int)ev.getX();
//        int y = (int)ev.getY();
//
//        Log.d("TouchEvent", "X:" + ev.getX() + ",Y:" + ev.getY());
//
//        switch (action){
//            case MotionEvent.ACTION_DOWN:
//                int i = 0;
//
//                for(; i < taxies.size(); i++){
//                    Taxi taxi = taxies.get(i);
//                    int taxix = taxi.playerX;
//                    int taxiy = taxi.playerY;
//                    int taxih = taxi.height;
//                    int taxiw = taxi.width;
//                    if (x >= taxix && x <= taxix + taxiw && y >= taxiy && y <= taxiy + taxih);{
//                        taxies.remove(i);
//                    }
//                    break;
//                }
//        }
//
//
//
//        return true;
//    }

