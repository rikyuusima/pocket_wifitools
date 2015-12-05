package mm.network_test;

import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class NetworkActivity extends AppCompatActivity implements ConnectionReceiver.Observer, View.OnClickListener {
        TextView tv;
        int i;
@Override
protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_network);

        IntentFilter filter = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        ConnectionReceiver receiver = new ConnectionReceiver(this);
        registerReceiver(receiver, filter);
        tv =(TextView)findViewById(R.id.search_text);
        Button bt = (Button) findViewById(R.id.test_button);
        bt.setOnClickListener(this);
        }
@Override
public void onClick(View view) {
        if(i==1){
        tv.setText("接続可能");
        }else {
        tv.setText("接続不可");
        }

        }


@Override
public int onConnect() {
        i=1;
        return i;
        }

@Override
public int onDisconnect() {
        i=0;
        return i;
        }

        }
