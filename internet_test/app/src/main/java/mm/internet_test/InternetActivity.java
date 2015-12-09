package mm.internet_test;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class InternetActivity extends AppCompatActivity  {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);


        //Button
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            //Button クリックリスナー
            @Override
            public void onClick(View v) {
                NewAsyncTask task = new NewAsyncTask();
                task.execute();
            }
        });
    }
}

  /*  class NewAsyncTask extends AsyncTask<String, Void, Integer> {
        String fURL = "http://www.google.co.jp/";
        TextView txt = null;
        int i;

        @Override
        protected Integer doInBackground(String... params) {
            try {
                //URLクラスのインスタンス作成
                URL url = new URL(fURL);
                //コネクションを開く。
                URLConnection con = url.openConnection();
                //インターネットに接続
                //con.getInputStream();
                i=1;
            }catch (IOException e) {
                //例外の場合はfalseを返す
                i=0;
                return i;
            }
            return i;
        }

        @Override
        protected void onPostExecute(Integer result) {
            if(i==1){
                txt.setText("接続可能");
            }else {
                txt.setText("接続不可");
            }
        }
    }*/
