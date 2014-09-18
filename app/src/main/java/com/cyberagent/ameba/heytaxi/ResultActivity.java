package com.cyberagent.ameba.heytaxi;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by itou on 2014/09/17.
 */

public class ResultActivity extends Activity implements View.OnClickListener{


    TextView text_count;
    Button button_title;
    Button button_game;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //タイトルバーを隠す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ステータスバーを隠す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        init();


        Intent intent = getIntent();
        int result = intent.getIntExtra("RESULT", 0);


        text_count.setText("タクシーを " + result + "台消しました！おめでとう！");
        button_game.setOnClickListener(this);
        button_title.setOnClickListener(this);
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

    public void init() {
        text_count = (TextView)findViewById(R.id.result);
        button_game = (Button)findViewById(R.id.button_game);
        button_title = (Button)findViewById(R.id.button_title);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.button_game:
                goGame();
                break;

            case R.id.button_title:
                goTitle();
                break;
        }
    }

    public void goGame(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }

    public void goTitle(){
        Intent intent = new Intent(this, TitleActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        this.startActivity(intent);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        if (event.getAction()==KeyEvent.ACTION_DOWN) {
            switch (event.getKeyCode()) {
                case KeyEvent.KEYCODE_BACK:
                    // ダイアログ表示など特定の処理を行いたい場合はここに記述
                    // 親クラスのdispatchKeyEvent()を呼び出さずにtrueを返す
                    return true;
            }
        }
        return super.dispatchKeyEvent(event);
    }

}