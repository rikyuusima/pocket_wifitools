package rs.wifitools_matome;

import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Wifi_strtypeメソッド.SSIDをString型で利用するメソッド群を記述.
 */
public class Wifi_strtype extends AppCompatActivity{
    WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
    WifiInfo info = manager.getConnectionInfo();
    /*Property*/
    String ssid_in;
    /*Constractor*/
    Wifi_strtype(String ssid_in)
    {
        this.ssid_in = ssid_in;
    }

    public boolean wifimatch()//WifiConfiguration型で与えられたWiFiと現在接続しているWiFiを比較する
    {
        if (info.getSSID().replace("\"","").equals(ssid_in.replace("\"","")))
            return true;
        else
            return false;
    }
    public void toolsmode_end()
    {
        manager.startScan();
        for (WifiConfiguration c0 : manager.getConfiguredNetworks()) {
            manager.enableNetwork(c0.networkId, false);
        }
        return ;
    }
}
