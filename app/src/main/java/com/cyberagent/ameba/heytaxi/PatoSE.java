package com.cyberagent.ameba.heytaxi;

import android.content.Context;
import android.media.AudioManager;
import android.media.SoundPool;

/**
 * Created by Yamada on 2014/09/18.
 */
public class PatoSE {
    private SoundPool soundPool;
    public int patose ;// 読み込んだ効果音
    public PatoSE(Context context)
    {
        // new SoundPool(読み込むファイル数,読み込む種類,読み込む質)
        this.soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        // load(コンテキスト,読み込むリソースID,音の優先度)
        this.patose = soundPool.load(context, R.raw.finish1, 1);
    }

    public void playSe()
    {
        // play(再生するサウンドID,左のボリューム,右のボリューム,優先度,ループ回数(0はしない、-1は無限),再生レート)
        //soundPool.play(taxise[0], 1.0f, 1.0f, 1, 0, 1.0f);
        soundPool.play(patose, 1.0f, 1.0f, 1, 0, 1.0f);
    }
}
