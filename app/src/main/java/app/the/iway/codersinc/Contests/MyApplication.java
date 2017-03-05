package app.the.iway.codersinc.Contests;

/**
 * Created by Shashank on 25-Sep-16.
 */
import android.app.Application;

public class MyApplication extends Application {

    private static MyApplication mInstance;
    public static int c=0;

    @Override
    public void onCreate() {
        super.onCreate();

        mInstance= (MyApplication) getApplicationContext();
    }

    public static synchronized MyApplication getInstance() {
        return mInstance;
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {

        ConnectivityReceiver.connectivityReceiverListener = listener;
    }

}
