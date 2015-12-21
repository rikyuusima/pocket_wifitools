package rs.toggle_button;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /*Switchリスナ群*/
        CompoundButton.OnCheckedChangeListener sw1Lis = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView tv = (TextView)findViewById(R.id.debugview);
                if (isChecked) {
                    tv.setText("o");
                }
                else {
                    tv.setText("x");
                }
            }
        };
        /*Switchリスナのインスタンス渡し*/
        Switch sw1 =(Switch)findViewById(R.id.switch1);
        sw1.setOnCheckedChangeListener(sw1Lis);
    }
}
