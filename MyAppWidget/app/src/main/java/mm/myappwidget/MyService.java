package mm.myappwidget;

import java.util.*;


import android.app.*;
import android.appwidget.AppWidgetManager;
import android.content.*;
import android.os.IBinder;
import android.widget.RemoteViews;


public class MyService extends Service {
    private static final String ACTION_BTNCLICK =
            "jp.tuyano.MyService.ACTION_BTNCLICK";

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);

        AppWidgetManager manager = AppWidgetManager.getInstance(this);


        RemoteViews view = new RemoteViews(getPackageName(), R.layout.appwidget);
        if (ACTION_BTNCLICK.equals(intent.getAction())) {
            btnClicked(view);
        }

        Intent newintent = new Intent();
        newintent.setAction(ACTION_BTNCLICK);
        PendingIntent pending = PendingIntent.getService(this, 0, newintent, 0);
        view.setOnClickPendingIntent(R.id.button1, pending);
        ComponentName widget = new ComponentName(this, WidgetActivity.class);
        manager.updateAppWidget(widget, view);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public void btnClicked(RemoteViews view){
        String[] msg = new String[]{"Hello world!"};
        int n = new Random(new Date().getTime()).nextInt(msg.length);
        view.setTextViewText(R.id.text1, msg[n]);
    }


}