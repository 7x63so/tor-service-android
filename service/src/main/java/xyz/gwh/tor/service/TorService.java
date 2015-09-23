package xyz.gwh.tor.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;

import java.io.IOException;
import java.net.Socket;

import xyz.gwh.tor.ITorService;
import xyz.gwh.tor.config.Torrc;
import xyz.gwh.tor.jtorctl.JtorctlException;
import xyz.gwh.tor.jtorctl.JtorctlWrapper;
import xyz.gwh.tor.jtorctl.JtorctlWrapperImpl;
import xyz.gwh.tor.util.Logger;

/**
 * A simple service that routes signals to jtorctl.
 */
public class TorService extends Service {

    private JtorctlWrapper jtorctlWrapper;

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Logger.i("Starting a TorControlConnection...");

        try {
            Socket torSocket = new Socket("192.168.0.1", 80);
            torSocket.setSoTimeout(0);
            jtorctlWrapper = new JtorctlWrapperImpl(torSocket);
        } catch (JtorctlException | IOException e) {
            Logger.e("Unable to create a TorControlConnection: " + e.getMessage());
        }

        return START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Logger.i("Binding successful.");
        return binder;
    }

    private boolean isConnected() {
        return jtorctlWrapper != null;
    }

    private final ITorService.Stub binder = new ITorService.Stub() {
        @Override
        public void signal(String signal) throws RemoteException {
            Logger.i("Calling jtorctl.signal()");

            if (isConnected()) {
                try {
                    jtorctlWrapper.signal(signal);
                } catch (JtorctlException e) {
                    Logger.e(e.getMessage());
                }
            }
        }

        @Override
        public void newIdentity() throws RemoteException {
            Logger.i("Calling jtorctl.newIdentity()");

            if (isConnected()) {
                try {
                    jtorctlWrapper.newIdentity();
                } catch (JtorctlException e) {
                    Logger.e(e.getMessage());
                }
            }
        }

        @Override
        public void setConfig(Torrc config) throws RemoteException {
            Logger.i("Calling jtorctl.setConfig()");

            if (isConnected()) {
                try {
                    jtorctlWrapper.setConfig(config);
                } catch (JtorctlException e) {
                    Logger.e(e.getMessage());
                }
            }
        }

        @Override
        public void stopTor() throws RemoteException {
            Logger.i("Calling jtorctl.stopTor()");

            if (isConnected()) {
                try {
                    jtorctlWrapper.stopTor();
                } catch (JtorctlException e) {
                    Logger.e(e.getMessage());
                }
            }
        }

        @Override
        public void exit() throws RemoteException {
            Logger.i("Attempting to stop TorService.");
            stopSelf();
        }
    };
}