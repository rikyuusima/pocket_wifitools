package mm.internet_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

public class InternetActivity extends AppCompatActivity  {
    private final String fURL = "http://www.google.co.jp/";
    TextView txt = null;
    int i;

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
               if(doNet(fURL)==1){
                   txt.setText("接続可能");
               }else {
                   txt.setText("接続不可");
               }
            }
        });
    }

    private int doNet(String u){
        try {
            //URLクラスのインスタンス作成
            URL url = new URL(u);
            URLConnection con = url.openConnection();
            //FileOutputStream out = new FileOutputStream(u);
            //InputStream is = con.getInputStream();
            i=1;
            //取得した文字列を文字列にして返す。
           /* BufferedReader input =
                    new BufferedReader(new InputStreamReader(is));
            String line = "";

            while(  (line = input.readLine())!=null) {
                ret += line;
            }*/
            return i;

        }catch (IOException e) {
            //例外の場合はfalseを返す
            i=0;
            return i;
        }
    }
}