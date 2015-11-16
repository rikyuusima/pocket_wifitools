package rs.wifitools_matome;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.net.wifi.WifiConfiguration;
import android.net.wifi.WifiManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class Set_SSID extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set__ssid);
        WifiManager manager = (WifiManager)getSystemService(WIFI_SERVICE);
        final List<WifiConfiguration> config_list = manager.getConfiguredNetworks();
        final String[] coli=new String[config_list.size()];
        for (int i=0;i<config_list.size();++i){
            coli[i]=config_list.get(i).SSID.replace("\"","");
        }
        ListView lv = (ListView)findViewById(R.id.ssid_listview);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,coli);
        lv.setAdapter(adapter);

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //ListView listView=(ListView)parent;
                Intent intent = new Intent();
                intent.putExtra("key",position);
                setResult(Activity.RESULT_OK,intent);

                finish();
            }
        });
    }

}
