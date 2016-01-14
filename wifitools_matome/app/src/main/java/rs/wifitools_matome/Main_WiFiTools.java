package rs.wifitools_matome;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.wifi.ScanResult;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;


public class Main_WiFiTools extends AppCompatActivity {

    private int configlistvar=-1;
    //private SharedPreferences data = getSharedPreferences("DataSave", Context.MODE_PRIVATE);
    //private SharedPreferences.Editor editor = data.edit();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__wi_fi_tools);
        Switch sw1s = (Switch)findViewById(R.id.replacesw);
        //sw1s.setChecked(data.getBoolean("sw1isChecked",false));
        if(sw1s.isChecked()){
            //動作させる
        }
        /*if(data.getString("PrioritySSID",null) != null) {
            TextView priv = (TextView) findViewById(R.id.priorityview);
            priv.setText("Priority: " + data.getString("PrioritySSID",null));
        }*/

        final Context context = this;

        /*Buttonリスナ群*/
        View.OnClickListener bt1Lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                set_ssid_open();
            }
        };
        View.OnClickListener bt2Lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView debv = (TextView) findViewById(R.id.debugview);
                TextView chav = (TextView) findViewById(R.id.changedview);
                WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
                WifiInfo info = manager.getConnectionInfo();
                List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
                WifiConfiguration config = config_list.get(configlistvar);
                //String priority_ssid = data.getString("PrioritySSID",null);
                manager.startScan();

                boolean replaceflag = false;
                boolean matchflag = false;

                debv.setText("");
                /*Scanされた接続可能なWiFiの数ループする*/
                for(ScanResult result : manager.getScanResults()) {
                    if(configlistvar != -1){
                        //Wifi_strtypeのインスタンス作成
                        Wifi_strtype wstr = new Wifi_strtype(context,result.SSID);
                        if(!wstr.wifimatch()) {
                            if (result.SSID.equals(config.SSID.replace("\"",""))) {
                                String oldSSID = info.getSSID().replace("\"", "");
                                //Wifi_wcontypeのインスタンス作成
                                Wifi_wcontype wcon = new Wifi_wcontype(context,config);
                                wcon.wifichange();
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
                            chav.setText("Setting SSID and Connecting SSID are Matched.");
                        }
                    }
                }

            }
        };
        View.OnClickListener bt3Lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Webaccess webac = new Webaccess(context);
                TextView webisv = (TextView)findViewById(R.id.webisenableview);
                if(!webac.IsEnabled())
                {
                    webisv.setText("False");
                    WifiManager manager = (WifiManager)getSystemService(WIFI_SERVICE);
                    WifiInfo info = manager.getConnectionInfo();
                    List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
                    WifiConfiguration config = config_list.get(configlistvar);
                    TextView wchav = (TextView)findViewById(R.id.web_changedview);
                    //String priority_ssid = data.getString("PrioritySSID",null);
                    //manager.startScan();

                    boolean replaceflag = false;
                    boolean matchflag = false;

                    /*Scanされた接続可能なWiFiの数ループする*/
                    for(ScanResult result : manager.getScanResults()) {
                        if(configlistvar != -1){
                            //Wifi_strtypeのインスタンス作成
                            Wifi_strtype wstr = new Wifi_strtype(context,result.SSID);
                            if(!wstr.wifimatch() && !replaceflag) {
                                if (result.SSID.equals(config.SSID.replace("\"",""))) {
                                    String oldSSID = info.getSSID().replace("\"", "");
                                    //Wifi_wcontypeのインスタンス作成
                                    Wifi_wcontype wcon = new Wifi_wcontype(context,config);
                                    wcon.wifichange();
                                /*for (WifiConfiguration c0 : manager.getConfiguredNetworks()) {
                                    if (!config.SSID.equals(c0.SSID)) {
                                    manager.enableNetwork(c0.networkId, false);
                                    }
                                }*/
                                    wchav.setText(oldSSID + " → " + config.SSID.replace("\"", ""));
                                    webisv.setText("True");
                                    replaceflag=true;
                                }
                                else if(!replaceflag){
                                    wchav.setText("Can not Resolve Setting SSID from Connectable WiFi.");
                                }
                                matchflag=true;
                            }
                            else if(!matchflag){
                                wchav.setText("Setting SSID and Connecting SSID are Matched.");
                            }
                        }
                    }
                }
            }
        };
        /*Buttonリスナのインスタンス渡し*/
        findViewById(R.id.setssidbt).setOnClickListener(bt1Lis);
        findViewById(R.id.replacebt).setOnClickListener(bt2Lis);
        findViewById(R.id.webisenabledbt).setOnClickListener(bt3Lis);

        /*Switchリスナ群*/
        CompoundButton.OnCheckedChangeListener sw1Lis = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    Intent notificationIntent = new Intent(context,Main_WiFiTools.class);
                    PendingIntent contentIntent = PendingIntent.getActivity(context,0,notificationIntent,0);

                    NotificationCompat.Builder builder = new NotificationCompat.Builder(getApplicationContext());
                    builder.setSmallIcon(R.mipmap.ic_launcher);
                    builder.setContentTitle("WiFi 優先切替モード");
                    builder.setContentText("タップしてアプリを開く");
                    builder.setNumber(1);
                    builder.setContentIntent(contentIntent);
                    builder.setAutoCancel(true);

                    NotificationManagerCompat nm = NotificationManagerCompat.from(getApplicationContext());
                    nm.notify(1,builder.build());
                    //editor.putBoolean("sw1isChecked", true);
                    //editor.apply();
                }
                else {
                    //editor.putBoolean("sw1isChecked",false);
                    //editor.apply();
                }
                Toast.makeText(Main_WiFiTools.this, "isChecked : " + isChecked, Toast.LENGTH_SHORT).show();
            }
        };
        /*Switchリスナのインスタンス渡し*/
        Switch sw1=(Switch)findViewById(R.id.replacesw);
        sw1.setOnCheckedChangeListener(sw1Lis);
    }

    public void onActivityResult( int requestCode, int resultCode, Intent intent )
    {
        // startActivityForResult()の際に指定した識別コードとの比較
        if( requestCode == 1001 ){

            // 返却結果ステータスとの比較
            if( resultCode == Activity.RESULT_OK ){

                // 返却されてきたintentから値を取り出す
                int configlistvar=intent.getIntExtra("key",-1);
                TextView chav = (TextView) findViewById(R.id.changedview);
                TextView priv = (TextView) findViewById(R.id.priorityview);
                chav.setText("");
                if(configlistvar != -1) {
                    WifiManager manager = (WifiManager) getSystemService(WIFI_SERVICE);
                    List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
                    String priority_ssid = config_list.get(configlistvar).SSID.replace("\"", "");
                    priv.setText("Priority: " + priority_ssid);
                    this.configlistvar = configlistvar;
                    //優先するWiFiのSSIDをデータ保存
                    //editor.putString("PrioritySSID", priority_ssid);
                    //editor.apply();
                }
                else {
                    this.configlistvar = configlistvar;
                    //editor.putString("PrioritySSID",null);
                    //editor.apply();
                }
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

}
