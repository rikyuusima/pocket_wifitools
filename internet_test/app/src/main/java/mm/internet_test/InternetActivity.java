package mm.internet_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class InternetActivity extends AppCompatActivity  {
    private TextView txt = null;
    private final static String fURL = "http://www.google.co.jp/";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_internet);

        //TextView
        txt = (TextView)findViewById(R.id.txt);

        //Button
        Button btn = (Button)findViewById(R.id.btn);
        btn.setOnClickListener(new View.OnClickListener() {
            //Button クリックリスナー
            @Override
            public void onClick(View v) {
                //URL先からダウンロードして、textviewにソース表示
                txt.setText(doNet(fURL));
            }
        });
    }

    //ネット接続
    private String doNet(String u)
    {
        //戻り値用文字列
        String ret="";

        try {
            //URLクラスのインスタンス作成
            URL url = new URL(u);
            //コネクションを開く。
            URLConnection con = url.openConnection();

            //接続先のデータを取得。
            InputStream is = con.getInputStream();

            //取得した文字列を文字列にして返す。
            BufferedReader input =
                    new BufferedReader(new InputStreamReader(is));
            String line = "";

            while(  (line = input.readLine())!=null)
                ret += line;

            return ret;

        } catch (MalformedURLException e) {
            //URLが間違っている場合はこの例外が発生
            //例外の場合は空文字を返す
            return ret;
        } catch (IOException e) {
            //例外の場合は空文字を返す
            return ret;
        }
    }
}