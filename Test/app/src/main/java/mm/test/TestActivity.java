package mm.test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class TestActivity extends AppCompatActivity {
    int i;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //TextView
        final TextView tv =(TextView)findViewById(R.id.search_test);
        //Button
        Button bt =(Button)findViewById(R.id.test_button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doNet()==1){
                    tv.setText("接続可能");
                }else {
                    tv.setText("接続不可");
                }
            }
        });
    }

    public int doNet(){
        try{
            URL url =new URL("http://www.google.co.jp");
            HttpURLConnection http =(HttpURLConnection)url.openConnection();
            http.setRequestMethod("GET");
            http.disconnect();
            i=1;
            return i;
        }catch (Exception e){
            i=0;
            return i;
        }
    }
}
