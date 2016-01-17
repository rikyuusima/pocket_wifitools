package mm.myappwidget;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        /*Buttonリスナ群*/
        View.OnClickListener bt1Lis = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView tv=(TextView)findViewById(R.id.button);
                tv.setText("Hello world!!!");
            }
        };
        /*Buttonリスナのインスタンス渡し*/
        findViewById(R.id.button).setOnClickListener(bt1Lis);
    }
}
