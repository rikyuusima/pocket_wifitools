package rs.toggle_button;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.TextView;

/**
 * Created by User on 2015/12/17.
 */
public class CheckedChanged implements CompoundButton.OnCheckedChangeListener{
    Context context;
    CheckedChanged(Context context){
        this.context=context;
    }
    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
        TextView tv = (TextView) ((rs.toggle_button.MainActivity) context).findViewById(R.id.debugview);
        if(isChecked){
            tv.setText("o");
        }
        else{
            tv.setText("x");
        }
        return ;
    }
}
