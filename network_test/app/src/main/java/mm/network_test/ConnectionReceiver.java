package mm.network_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import java.util.Observer;

public class ConnectionReceiver extends BroadcastReceiver {
    private Observer mObserver;

    public ConnectionReceiver(Observer observer) {
        mObserver = observer;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo info = manager.getActiveNetworkInfo();
        if(info != null && info.isAvailable()){
           mObserver.onConnect();
        }else{
            mObserver.onDisconnect();
        }
    }

    interface Observer {
        int onConnect();
        int onDisconnect();
    }
}
