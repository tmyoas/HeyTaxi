package com.cyberagent.ameba.heytaxi;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

/**
 * Created by itou on 2014/09/17.
 */

public class ResultActivity extends Activity implements View.OnTouchListener{


//    TextView text_count;
    ImageView image_game;
    ImageView image_title;
    ImageView put_num0;
    ImageView put_num1;
    ImageView put_num2;
    ImageView put_num3;
    Bitmap touch_game;
    Bitmap touch_title;
    Bitmap def_game;
    Bitmap def_title;
    Bitmap num1;
    Bitmap num2;
    Bitmap num3;
    Bitmap num4;
    Bitmap num5;
    Bitmap num6;
    Bitmap num7;
    Bitmap num8;
    Bitmap num9;



//    ArrayList<Integer> result_split = new ArrayList<Integer>();

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        //タイトルバーを隠す
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //ステータスバーを隠す
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_result);
        init();

        ImageView[] put_num = new ImageView[]{put_num3, put_num2, put_num1, put_num0};

        Intent intent = getIntent();
        int result = intent.getIntExtra("RESULT", 0);

        int result1 = result % 10;
        int result2 = (result % 100) / 10;
        int result3 = (result % 1000) / 100;
        int result4 = (result % 10000) / 1000;

        int[] result_array = new int[]{result4, result3, result2, result1};

        Log.v("CHECK", "" + result);

        for (int i = 0 ; i < result_array.length ; i++){
//            setNum(i, put_num[result_split.length - i]);
            setNum(result_array[i], put_num[i]);
        }



//        text_count.setText("消したタクシー : " + result + "\n" + "交通マナーは守りましょう");

        image_title.setOnTouchListener(this);
        image_game.setOnTouchListener(this);

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
//        text_count = (TextView)findViewById(R.id.result);
        image_game = (ImageView)findViewById(R.id.image_game);
        image_title = (ImageView)findViewById(R.id.image_title);
        put_num0 = (ImageView)findViewById(R.id.result_num0);
        put_num1 = (ImageView)findViewById(R.id.result_num1);
        put_num2 = (ImageView)findViewById(R.id.result_num2);
        put_num3 = (ImageView)findViewById(R.id.result_num3);
        Resources res = getResources();
        touch_game = BitmapFactory.decodeResource(res, R.drawable.return_game_down);
        touch_title = BitmapFactory.decodeResource(res, R.drawable.return_title_down);
        def_game = BitmapFactory.decodeResource(res, R.drawable.return_game_default);
        def_title = BitmapFactory.decodeResource(res, R.drawable.return_title_default);
        num1 = BitmapFactory.decodeResource(res, R.drawable.num_1);
        num2 = BitmapFactory.decodeResource(res, R.drawable.num_2);
        num3 = BitmapFactory.decodeResource(res, R.drawable.num_3);
        num4 = BitmapFactory.decodeResource(res, R.drawable.num_4);
        num5 = BitmapFactory.decodeResource(res, R.drawable.num_5);
        num6 = BitmapFactory.decodeResource(res, R.drawable.num_6);
        num7 = BitmapFactory.decodeResource(res, R.drawable.num_7);
        num8 = BitmapFactory.decodeResource(res, R.drawable.num_8);
        num9 = BitmapFactory.decodeResource(res, R.drawable.num_9);

    }

    @Override
    public boolean onTouch(View view, MotionEvent event) {
        switch (view.getId()) {
            case R.id.image_game:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image_game.setImageBitmap(touch_game);
                        break;
                    case MotionEvent.ACTION_UP:
                        image_game.setImageBitmap(def_game);
                        goGame();
                        break;
                }
                break;

            case R.id.image_title:
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:
                        image_title.setImageBitmap(touch_title);
                        break;
                    case MotionEvent.ACTION_UP:
                        image_title.setImageBitmap(def_title);
                        goTitle();
                        break;
                }
                break;
        }
        return true;
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

    public void setNum(int num, ImageView imageview){
        switch (num) {
            case 1:
                imageview.setImageBitmap(num1);
                break;
            case 2:
                imageview.setImageBitmap(num2);
                break;
            case 3:
                imageview.setImageBitmap(num3);
                break;
            case 4:
                imageview.setImageBitmap(num4);
                break;
            case 5:
                imageview.setImageBitmap(num5);
                break;
            case 6:
                imageview.setImageBitmap(num6);
                break;
            case 7:
                imageview.setImageBitmap(num7);
                break;
            case 8:
                imageview.setImageBitmap(num8);
                break;
            case 9:
                imageview.setImageBitmap(num9);
                break;
            default:
                break;

        }
        
    }
}