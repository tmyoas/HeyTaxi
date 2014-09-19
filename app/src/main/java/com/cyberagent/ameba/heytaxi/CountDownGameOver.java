package com.cyberagent.ameba.heytaxi;

/**
 * Created by itou on 2014/09/15.
 */
public class CountDownGameOver {
    int  count_over;
    boolean gameend;

    CountDownGameOver () {
        count_over = 9;
    }

    //タクシーがタッチされないまま画面上端に到達したときの処理
    public boolean touchline(){
        //呼び出されたとき対応したlaneの残り台数を減らす
        count_over -= 1;

        //ゲーム終了の判定
        switch (count_over){
            case 0:
                gameend = true;
            break;

            default:
                gameend = false;
        }
        //残り台数が0の時true、それ以外の時falseを返す
        return gameend;
    }
}
