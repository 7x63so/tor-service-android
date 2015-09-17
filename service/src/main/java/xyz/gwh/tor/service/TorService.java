package xyz.gwh.tor.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import xyz.gwh.tor.ITorService;
import xyz.gwh.tor.config.Torrc;

/**
 * @author gwh
 */
public class TorService extends Service {

    private void log(String message) {
        Log.v("MainService", message);
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        log("Received start command.");
        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        log("Received binding.");
        return mBinder;
    }

    private final ITorService.Stub mBinder = new ITorService.Stub() {
        @Override
        public void setConfig(Torrc config) throws RemoteException {
            log("Received setConfig command.");
            log(config.toString());
        }

        @Override
        public void exit() throws RemoteException {
            log("Received exit command.");
            stopSelf();
        }
    };
}