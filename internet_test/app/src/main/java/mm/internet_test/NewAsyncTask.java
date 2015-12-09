package mm.internet_test;

import android.os.AsyncTask;
import android.widget.TextView;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;

public class NewAsyncTask extends AsyncTask<String, Void, Integer> {
     String fURL = "http://www.google.co.jp/";
    private TextView txt = null;
    int i;

    @Override
    protected Integer doInBackground(String... params) {
        try {
            //URLクラスのインスタンス作成
            URL url = new URL(fURL);
            //コネクションを開く。
            URLConnection con = url.openConnection();
            //インターネットに接続
            con.getInputStream();
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
}
