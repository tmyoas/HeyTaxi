package com.cyberagent.ameba.heytaxi;

/**
 * Created by itou on 2014/09/12.
 */

//Taxiの当たり判定に当たったときに呼び出し

public class CountDestroyTaxi{

    int count_destroy;

    //コンストラクタ
    CountDestroyTaxi () {
        count_destroy = 0;
    }

    //呼び出してカウント
    void increment() {
        count_destroy += 1;
    }

    //合計の出力
    int total(){
       return count_destroy;
    }
}
