package rs.wifitools_matome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Main_WiFiTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main__wi_fi_tools);
    }
    public void setssid_click(View v)
    {
        // 遷移先のActivityを指定して、Intentを作成する
        Intent intent = new Intent( this, Set_SSID.class );

        // 遷移先のアクティビティを起動させる
        startActivity(intent);
    }

}
