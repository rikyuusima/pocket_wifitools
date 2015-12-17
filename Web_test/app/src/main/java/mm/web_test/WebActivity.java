package mm.web_test;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.TextView;

public class WebActivity extends AppCompatActivity {
    //final String fURL ="http://rsserver.web.fc2.com/";
    final String fURL = "http://www.google.co.jp/";
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
        final String title = "Google";
        final TextView dv = (TextView)findViewById(R.id.debugView);
        String scan_title;
        WebView webView01 = new WebView(this);
        webView01.getSettings().setJavaScriptEnabled(false);
        webView01.loadUrl(fURL);
        long start = System.currentTimeMillis();
        long end;
        do {
            scan_title = webView01.getTitle();
            end = System.currentTimeMillis();
            if(5000<=(end-start)) {
                scan_title = "Can not connect";
                break;
            }
        }while (scan_title == null);

        dv.setText("<title>"+scan_title+"</title>");

        if(scan_title.equals(title)){
            return true;
        }
        else{
            return false;
        }
    }
}
