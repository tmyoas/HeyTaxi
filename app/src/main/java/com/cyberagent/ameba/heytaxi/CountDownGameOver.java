package com.cyberagent.ameba.heytaxi;

/**
 * Created by itou on 2014/09/15.
 */
public class CountDownGameOver {

//    int[] count_over = new int[]{5, 5, 5, 5, 5};
    int[] count_over = new int[5];
    boolean gameend;

    CountDownGameOver () {
        count_over = new int[]{5, 5, 5, 5, 5};
    }

    //タクシーがタッチされないまま画面上端に到達したときの処理
    public boolean touchline(int lane){

        count_over[lane] -= 1;

        //ゲーム終了の判定
        switch (count_over[lane]){
            case 0:
                gameend = true;
            break;

            default:
                gameend = false;
        }
        return gameend;
    }
}
