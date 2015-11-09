package rs.wifisearch_test;

import android.net.wifi.ScanResult;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class SsidsearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ssidsearch);
    }
    public void searchbutton(View view){
        TextView tv=(TextView)findViewById(R.id.ssid);
        tv.setText("");
        WifiManager manager=(WifiManager)getSystemService(WIFI_SERVICE);
        manager.startScan();
        for(ScanResult result : manager.getScanResults()){
             tv.setText(tv.getText()+result.SSID+"\n");
        }
    }
}
