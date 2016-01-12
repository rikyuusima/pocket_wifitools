package rs.wifitools_matome;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;

/**
 * Webaccessメソッド.引数なしのメソッド群を記述
 */
public class Webaccess {
    Context context;
    Webaccess(Context context)
    {
        this.context = context;
    }
    public boolean IsEnabled()
    {
        final String fURL ="http://rsserver.web.fc2.com/";//アクセスするWebサイトのURL
        final String title = "rsserver";//アクセスするWebサイトのタイトル
        final String disable_str = "Can not connect";//タイムアウト時に付けるフラグ
        String scan_title;
        WebView webView01 = new WebView(context);
        webView01.getSettings().setJavaScriptEnabled(false);
        webView01.getSettings().setCacheMode(WebSettings.LOAD_NO_CACHE);
        webView01.loadUrl(fURL);
        long start = System.currentTimeMillis();//タイムアウト計測用
        long end;//タイムアウト計測用
        do {
            scan_title = webView01.getTitle();
            end = System.currentTimeMillis();
            /*タイムアウト処理*/
            if(5000<=(end-start)) {
                scan_title = disable_str;
                break;
            }
        }while (scan_title == null);

        if(scan_title.equals(title))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}
