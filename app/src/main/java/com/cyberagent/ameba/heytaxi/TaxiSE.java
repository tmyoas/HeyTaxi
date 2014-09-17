package com.cyberagent.ameba.heytaxi;



/**
 * Created by Yamada on 2014/09/17.
 */import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

import java.util.ArrayList;

public class TaxiSE
{
    private SoundPool soundPool;
    public int[] taxise = new int[5];// 読み込んだ効果音
    public TaxiSE(Context context)
    {
        // new SoundPool(読み込むファイル数,読み込む種類,読み込む質)
        this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        // load(コンテキスト,読み込むリソースID,音の優先度)
        this.taxise[0] = soundPool.load(context, R.raw.baku027, 1);
        this.taxise[1] = soundPool.load(context, R.raw.crash, 1);
        this.taxise[2] = soundPool.load(context, R.raw.ta_ta_dokan01, 1);
        this.taxise[2] = soundPool.load(context, R.raw.sen_mi_lasergun03, 1);

    }

    public void playSe(int index)
    {
        // play(再生するサウンドID,左のボリューム,右のボリューム,優先度,ループ回数(0はしない、-1は無限),再生レート)
        //soundPool.play(taxise[0], 1.0f, 1.0f, 1, 0, 1.0f);
        soundPool.play(taxise[index], 1.0f, 1.0f, 1, 0, 1.0f);
    }
}