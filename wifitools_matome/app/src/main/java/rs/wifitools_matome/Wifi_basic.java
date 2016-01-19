package rs.wifitools_matome;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;

/**
 * Wifi_basicクラス.Wifi系のAndroid内の基本設定を変更させるメソッド群を記述.
 */
public class Wifi_basic {
    Context context;

    Wifi_basic (Context context){
        this.context = context;
    }
    public void toolsmode_end()//WiFiの自動接続を無効化したものを元に戻す
    {
        WifiManager manager = (WifiManager)context.getSystemService(Context.WIFI_SERVICE);
        manager.startScan();
        for (WifiConfiguration c0 : manager.getConfiguredNetworks()) {
            manager.enableNetwork(c0.networkId, false);
        }
    }
}
