package mm.web_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    String fURL ="http://www.google.co.jp/";
    //static String n = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_web);

        final TextView tv =(TextView)findViewById(R.id.textView);
        Button bt =(Button)findViewById(R.id.button);
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(doNet()){
                   tv.setText("接続可能");
                }else{
                   tv.setText("接続不可");
                }
            }
        });

    }

    public boolean doNet() {
        final TextView tv =(TextView)findViewById(R.id.textView);
        String a;
        WebView webView01 = new WebView(this);
        webView01.getSettings().setJavaScriptEnabled(false);
        webView01.loadUrl(fURL);

        do {
            a = webView01.getTitle();
        }while (a == null);
        //n =a;

        //tv.setText("hoge"+a+"hoge");

        if(a.equals("Webpage not available")){
            return false;
        }else{
            return true;
        }
    }
}
