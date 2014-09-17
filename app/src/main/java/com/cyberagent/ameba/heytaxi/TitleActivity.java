package com.cyberagent.ameba.heytaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.Window;
import android.view.WindowManager;

/**
 * Created by itou on 2014/09/17.
 */
public class TitleActivity extends Activity {
    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //タイトルバーを隠す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ステータスバーを隠す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

//    public void onClick(View v) {
//        Intent intent = new Intent(this, com.cyberagent.ameba.heytaxi.MainActivity.class);
//        this.startActivity(intent);
//    }

    public boolean onTouchEvent(MotionEvent ev){
        Intent intent = new Intent(this, com.cyberagent.ameba.heytaxi.MainActivity.class);
        this.startActivity(intent);
        return false;
    }

}
