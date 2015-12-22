package rs.wifitools_matome;

import android.content.Context;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;

/**
 * Wifi_strtypeメソッド.SSIDをString型で利用するメソッド群を記述.
 */
public class Wifi_strtype extends AppCompatActivity{
    /*Property*/
    Context context;
    String ssid_in;

    /*Constructor*/
    Wifi_strtype(Context context,String ssid_in)
    {
        this.context = context;
        this.ssid_in = ssid_in;
    }

    /*Method*/
    public boolean wifimatch()//与えられたWiFiのSSIDと現在接続しているWiFiのSSIDを比較する
    {
        WifiManager manager = (WifiManager)context.getSystemService(WIFI_SERVICE);
        WifiInfo info = manager.getConnectionInfo();
        if (info.getSSID().replace("\"","").equals(ssid_in/*.replace("\"","")*/))
            return true;
        else
            return false;
    }
    public void toolsmode_end()
    {
        WifiManager manager = (WifiManager)context.getSystemService(WIFI_SERVICE);
        manager.startScan();
        for (WifiConfiguration c0 : manager.getConfiguredNetworks()) {
            manager.enableNetwork(c0.networkId, false);
        }
        return ;
    }
}
