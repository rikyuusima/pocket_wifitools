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
import android.widget.TextView;

import java.util.List;


public class Main_WiFiTools extends AppCompatActivity {

    private int configlistvar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__wi_fi_tools);
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

    public void setssid_click(View v)
    {
        // 遷移先のActivityを指定して、Intentを作成する
        Intent intent = new Intent( this, Set_SSID.class );
        int requestCode=1001;

        // 遷移先のアクティビティを起動させる
        startActivityForResult(intent, requestCode);
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
                if(!info.getSSID().replace("\"","").equals(result.SSID)) {
                    if (result.SSID.equals(config.SSID.replace("\"", ""))) {
                        String oldSSID = info.getSSID().replace("\"", "");

                        //現在の接続の強制的な切断
                        manager.disableNetwork(info.getNetworkId());
                        //強制的な切断をして無効化したWiFiを有効化させる(OSのWiFi自動接続を阻害させない為)
                        manager.enableNetwork(info.getNetworkId(), false);
                        manager.saveConfiguration();
                        manager.updateNetwork(config);
                        manager.enableNetwork(config.networkId, true);
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
