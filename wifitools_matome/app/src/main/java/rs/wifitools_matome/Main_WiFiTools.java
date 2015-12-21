package rs.wifitools_matome;

import android.app.Activity;
import android.content.Intent;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Main_WiFiTools extends AppCompatActivity {

    private int configlistvar;
    private boolean replaceswbool;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__wi_fi_tools);

        /*Switchリスナ群*/
        CompoundButton.OnCheckedChangeListener sw1Lis = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked == true) {
                    replaceswbool=true;

                } else {
                    replaceswbool=true;
                }
                Toast.makeText(Main_WiFiTools.this, "isChecked : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        };
        /*Switchリスナのインスタンス渡し*/
        Switch sw1=(Switch)findViewById(R.id.replaceswitch);
        sw1.setOnCheckedChangeListener(sw1Lis);
    }
    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 1001 ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                configlistvar=intent.getIntExtra("key",-1);
                TextView chav = (TextView) findViewById(R.id.changedview);
                chav.setText("");
            }
        }
    }

    public void set_ssid_open()
    {
        // 遷移先のActivityを指定して、Intentを作成する
        Intent intent = new Intent( this, Set_SSID.class );
        int requestCode=1001;

        // 遷移先のアクティビティを起動させる
        startActivityForResult(intent, requestCode);
    }


    public void setssid_click(View v)
    {
        set_ssid_open();
    }

    public void replace_click(View v)
    {
            TextView debv = (TextView) findViewById(R.id.debugview);
            TextView chav = (TextView) findViewById(R.id.changedview);
            WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
            WifiInfo info = manager.getConnectionInfo();
            List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
            WifiConfiguration config = config_list.get(configlistvar);
            manager.startScan();

            boolean replaceflag = false;
            boolean matchflag = false;

        debv.setText("");

        for(ScanResult result : manager.getScanResults()) {
            if(configlistvar!=-1){
                //Wifi_strtypeのインスタンス作成
                Wifi_strtype wstr = new Wifi_strtype(result.SSID);
                if(!wstr.wifimatch()) {
                    if (result.SSID.equals(config.SSID.replace("\"", ""))) {
                        String oldSSID = info.getSSID().replace("\"", "");
                        //Wifi_wcontypeのインスタンス作成
                        Wifi_wcontype wtools = new Wifi_wcontype(config);
                        wtools.wifichange();
                        /*for (WifiConfiguration c0 : manager.getConfiguredNetworks()) {
                            if (!config.SSID.equals(c0.SSID)) {
                                manager.enableNetwork(c0.networkId, false);
                            }
                        }*/
                        chav.setText(oldSSID + " → " + config.SSID.replace("\"", ""));
                        replaceflag=true;
                    }
                    else if(!replaceflag){
                        chav.setText("Can not Resolve Setting SSID from Connectable WiFi.");
                    }
                    matchflag=true;
                }
                else if(!matchflag){
                    chav.setText("Setting SSID and Connecting SSID is Matched.");
                }
            }
        }
        
    }

}
