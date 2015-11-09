package com.maturu.ssid_history;

import android.net.wifi.WifiManager;
import android.net.wifi.WifiConfiguration;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.util.List;

public class historyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);
    }

    public void SearchHistory(View view) {
        TextView tv =(TextView)findViewById(R.id.history);
        tv.setText("");
        WifiManager manager = (WifiManager)getSystemService(WIFI_SERVICE);
        List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
        for(int i=0; i < config_list.size(); i++){
            tv.setText(tv.getText()+config_list.get(i).SSID+"\n");
        }
    }
}
