package mm.preferencesample1;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class PreferenceSample1Activity extends Activity {
    private final int REQUEST_CODE = 0;
    private static final int MENU_ITEM1 = Menu.FIRST;
    private TextView tv;

    // Preferencesデータを読み込みTextViewに表示する。
    private void readPreferences() {
        final String PREFERENCE_KEY = "edKey";
        SharedPreferences preferences = PreferenceManager
                .getDefaultSharedPreferences(this);
        String preferenceValue = preferences.getString(PREFERENCE_KEY, "未設定");
        tv.setText(PREFERENCE_KEY + "=" + preferenceValue);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        tv = new TextView(this);
        setContentView(tv);
        readPreferences();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuItem menuItem1 = menu.add(Menu.NONE, MENU_ITEM1, Menu.NONE, "設定");
        menuItem1.setIcon(android.R.drawable.ic_menu_preferences);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case MENU_ITEM1:
                Intent intent = new Intent(this, MyPreferenceActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
                return true;
        }
        return false;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == REQUEST_CODE) {
            readPreferences();
        }
    }
}