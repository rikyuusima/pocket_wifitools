package rs.wifitools_matome;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Wifi_wcontypeメソッド.WiFiConfiguration型を利用するメソッド群を記述.
 */
public class Wifi_wcontype extends AppCompatActivity{
    WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
    WifiInfo info;

    /*Property*/
    WifiConfiguration config;
    /*Constructor*/
    Wifi_wcontype(WifiConfiguration config)
    {
        this.config=config;
    }
    /*Method*/

    public void wifichange()//WifiConfiguration型で与えられたWiFiに切り替える
    {
        info = manager.getConnectionInfo();
        //現在の接続の強制的な切断
        manager.disableNetwork(info.getNetworkId());
        //強制的な切断をして無効化したWiFiを有効化させる(OSのWiFi自動接続を阻害させない為)
        //manager.enableNetwork(info.getNetworkId(), false);
        manager.enableNetwork(config.networkId, true);
        manager.saveConfiguration();
        manager.updateNetwork(config);
    }
}
