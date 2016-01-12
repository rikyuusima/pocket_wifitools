package mm.samplewidget01;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class MyWidgetIntentReceiver extends BroadcastReceiver {
    public static int clickCount = 0;

    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals("UPDATE_WIDGET")) {
            RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.main);

            // テキストをクリック回数を元に更新
            remoteViews.setTextViewText(R.id.title, "クリック回数: " + MyWidgetIntentReceiver.clickCount);

            // もう一回クリックイベントを登録(毎回登録しないと上手く動かず)
           // remoteViews.setOnClickPendingIntent(R.id.button, SampleWidget01.clickButton(context));

            SampleWidget01.pushWidgetUpdate(context.getApplicationContext(), remoteViews);
        }
    }
}