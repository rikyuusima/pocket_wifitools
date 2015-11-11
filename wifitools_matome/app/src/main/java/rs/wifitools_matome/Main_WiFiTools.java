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

    private String config_list=null;
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
                config_list = intent.getStringExtra( "key" );
            }
        }
    }

    public void setssid_click(View v)
    {
        // 遷移先のActivityを指定して、Intentを作成する
        Intent intent = new Intent( this, Set_SSID.class );
        int requestCode=1001;

        // 遷移先のアクティビティを起動させる
        startActivityForResult(intent,requestCode);
    }
    public void replace_click(View v)
    {
        WifiManager manager = (WifiManager)getSystemService(WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        manager.startScan();
        for(ScanResult result : manager.getScanResults()) {
            if(config_list!=null){
                if (result.SSID.equals(config_list) && !info.getSSID().equals(result.SSID)) {
                    //現在の接続の強制的な切断
                    manager.disableNetwork(info.getNetworkId());
                    //接続の関連付け
                    manager.enableNetwork(info.getNetworkId(), false);
                    WifiConfiguration config = new WifiConfiguration();
                    config.SSID = config_list;
                    config.hiddenSSID = true;
                    config.status = WifiConfiguration.Status.ENABLED;
                    config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.TKIP);
                    config.allowedGroupCiphers.set(WifiConfiguration.GroupCipher.CCMP);
                    config.allowedKeyManagement.set(WifiConfiguration.KeyMgmt.WPA_PSK);
                    config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.TKIP);
                    config.allowedPairwiseCiphers.set(WifiConfiguration.PairwiseCipher.CCMP);
                    config.allowedProtocols.set(WifiConfiguration.Protocol.RSN);

                    if(manager.addNetwork(config)!=-1){
                        TextView tv = (TextView)findViewById(R.id.debugview);
                        tv.setText("WiFi Changed");
                    }
                    manager.addNetwork(config);
                    manager.saveConfiguration();
                    manager.updateNetwork(config);
                }
            }
        }
    }

}
