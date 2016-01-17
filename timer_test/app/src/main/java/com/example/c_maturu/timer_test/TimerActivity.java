package com.example.c_maturu.timer_test;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;

import java.util.Timer;
import java.util.TimerTask;
import android.os.Handler;
import java.util.logging.LogRecord;

public class TimerActivity extends ActionBarActivity {

    private Button btn1;
    private Button btn2;
    private TextView txt1;
    private int count = 0;

    private MyTimerTask timerTask = null;
    private Timer timer = null;
    private Handler handler = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);

        btn1 = (Button)findViewById(R.id.start_button);
        btn2 = (Button)findViewById(R.id.stop_button);
        txt1 = (TextView)findViewById(R.id.txt_counter);



        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast_Start();
                // 稼働中の場合は止める
                if(null != timer){
                    timer.cancel();
                    timer = null;
                }

                // タイマーインスタンスを作成
                timer = new Timer();

                // タイマータスクインスタンスを作成
                timerTask = new MyTimerTask();

                // タイマースケジュールを設定
                timer.schedule(timerTask, 1000, 100);

                // カウンタを初期化して設定
                count = 0;
                txt1.setText(String.valueOf(count));


            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast_Stop();
                if(null != timer){
                    // タイマーをキャンセル
                    timer.cancel();
                    timer = null;
                }
            }
        });
    }

    public void Toast_Start(){
        Toast toast = Toast.makeText(this, "＜稼働中＞", Toast.LENGTH_LONG);
        toast.show();
    }

    public void Toast_Stop(){
        Toast toast = Toast.makeText(this, "＜停止しました＞", Toast.LENGTH_LONG);
        toast.show();
    }

    //↓ここは関係なかった。アクションバーの設定部分
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }*/

    // タイマータスク用のクラス
    class MyTimerTask extends TimerTask{

        @Override
        public void run() {
            handler.post( new Runnable() {
                public void run() {
                    count++;
                    txt1.setText(String.valueOf(count));
                }
            });
        }
    }
}