package mm.ping_test;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class PingActivity extends AppCompatActivity implements View.OnClickListener{
    ConnectivityManager cm;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ping);

        cm = (ConnectivityManager) getSystemService(CONNECTIVITY_SERVICE);
        Button bt = (Button) findViewById(R.id.check_button);
        bt.setOnClickListener(this);
        tv = (TextView) findViewById(R.id.serch_text);
    }

    @Override
    public void onClick(View view) {
        NetworkInfo nInfo = cm.getActiveNetworkInfo();
        tv.setText("");

        if(nInfo == null){
            tv.setText("接続不可");
            return;
        }

        if(nInfo.isConnected()){
            tv.setText("接続可");
        }else{
            tv.setText("接続不可");
        }
    }
}
