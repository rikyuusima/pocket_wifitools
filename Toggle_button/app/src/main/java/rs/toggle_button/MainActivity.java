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
        new CheckedChanged(this);
        Switch s=(Switch)findViewById(R.id.switch1);
        s.setOnCheckedChangeListener(new CheckedChanged(this));
    }
}
