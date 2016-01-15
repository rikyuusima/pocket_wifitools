package rs.shared_preferences_test;

import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    SharedPreferences data = getSharedPreferences("my_preferences", 0);
    SharedPreferences.Editor editor = data.edit();
    int count = data.getInt("count", 0);
    TextView tv = (TextView) findViewById(R.id.varView);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv.setText(count+"回");

        View.OnClickListener bt1lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                count++;
                editor.putInt("count", count);
                editor.commit();
                tv.setText(count+"回");
            }
        };
        findViewById(R.id.countBt).setOnClickListener(bt1lis);
    }
}
