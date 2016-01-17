package mm.myappwidget;


import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;

public class WidgetActivity extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        Intent serviceIntent = new Intent(context, mm.myappwidget.MyService.class);
        context.startService(serviceIntent);
    }
}
