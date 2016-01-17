package rs.listener_standard;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    static int amount=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Buttonリスナ群*/
        View.OnClickListener bt1Lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv1 = (TextView)findViewById(R.id.textView1);
                ++amount;
                tv1.setText("amount : "+amount);
            }
        };
        /*Buttonリスナのインスタンス渡し*/
        findViewById(R.id.button1).setOnClickListener(bt1Lis);

        /*Switchリスナ群*/
        CompoundButton.OnCheckedChangeListener sw1Lis = new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                TextView tv2 = (TextView)findViewById(R.id.textView2);
                if (isChecked) {
                    tv2.setText("isChecked : " + isChecked);
                }
                else {
                    tv2.setText("isChecked : " + isChecked);
                }
            }
        };
        /*Switchリスナのインスタンス渡し*/
        Switch sw1 =(Switch)findViewById(R.id.switch1);
        sw1.setOnCheckedChangeListener(sw1Lis);
    }
}
