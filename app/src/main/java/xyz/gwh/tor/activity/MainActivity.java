package xyz.gwh.tor.activity;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import xyz.gwh.tor.ITorService;
import xyz.gwh.tor.R;
import xyz.gwh.tor.config.Torrc;

public class MainActivity extends Activity {

    private static final String SERVICE_PKG = "xyz.gwh.tor.service";
    private static final String SERVICE_CLASS = "xyz.gwh.tor.service.TorService";

    private TextView logText;
    private Button startButton;
    private Button newIdentityButton;
    private Button setConfigButton;
    private Button stopTorButton;
    private Button stopButton;

    private ITorService service;
    private Intent serviceIntent;
    private ServiceConnection connection;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bindViews();
        setActions();
        initServiceIntent();
        initServiceConnection();
    }

    private void bindViews() {
        logText = (TextView) findViewById(R.id.log_text);
        startButton = (Button) findViewById(R.id.start_service_btn);
        newIdentityButton = (Button) findViewById(R.id.new_identity_btn);
        setConfigButton = (Button) findViewById(R.id.set_config_btn);
        stopTorButton = (Button) findViewById(R.id.stop_tor_btn);
        stopButton = (Button) findViewById(R.id.stop_service_btn);
    }

    private void setActions() {
        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startService();
                bindService();
            }
        });

        newIdentityButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                newIdentity();
            }
        });

        setConfigButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setConfig();
            }
        });

        stopTorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stopTor();
            }
        });

        stopButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                stopService();
            }
        });
    }

    private void initServiceIntent() {
        serviceIntent = new Intent();
        serviceIntent.setComponent(new ComponentName(SERVICE_PKG, SERVICE_CLASS));
    }

    private void initServiceConnection() {
        connection = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName className, IBinder service) {
                logText.append("Service bound!\n");
                MainActivity.this.service = ITorService.Stub.asInterface(service);
                setConfig();
            }

            @Override
            public void onServiceDisconnected(ComponentName className) {
                service = null;
                // This method is only invoked when the service quits from the other end or gets killed
                // Invoking exit() from the AIDL interface makes the Service kill itself, thus invoking this.
                logText.append("Service disconnected.\n");
            }
        };
    }

    private void startService() {
        logText.setText("Starting service…\n");
        startService(serviceIntent);
    }

    private void stopService() {
        logText.append("Stopping service…\n");
        try {
            service.exit();
            logText.append("Service stopped");
        } catch (RemoteException e) {
            makeToast("stopService() failed!");
        }
    }

    private void bindService() {
        logText.append("Binding service…\n");
        bindService(serviceIntent, connection, BIND_AUTO_CREATE);
    }

    private void newIdentity() {
        try {
            service.newIdentity();
        } catch (RemoteException e) {
            makeToast("newIdentity() failed!");
        }
    }

    private void stopTor() {
        try {
            service.stopTor();
        } catch (RemoteException e) {
            makeToast("stopTor() failed!");
        }
    }

    private void setConfig() {
        try {
            service.setConfig(Torrc.DEFAULT_BUILDER.build());
        } catch (RemoteException e) {
            makeToast("setConfig() failed!");
        }
    }

    private void makeToast(String text) {
        Toast.makeText(this, text, Toast.LENGTH_SHORT).show();
    }
}