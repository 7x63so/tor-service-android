package xyz.gwh.tor.jtorctl;

import android.support.annotation.NonNull;

import net.freehaven.tor.control.EventHandler;
import net.freehaven.tor.control.TorControlConnection;

import java.io.IOException;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import xyz.gwh.tor.config.Torrc;


/**
 * An implementation of the {@link xyz.gwh.tor.jtorctl.JtorctlWrapper} interface.
 */
public class JtorctlWrapperImpl implements JtorctlWrapper {

    private static final String SIGNAL_NEWNYM = "NEWNYM";
    private static final String SIGNAL_RELOAD = "RELOAD";
    private static final String SIGNAL_HALT = "HALT";

    private static final List<String> EVENTS = Arrays.asList("ORCONN", "CIRC", "NOTICE", "WARN", "ERR", "BW");

    private static final String ERR_SIGNAL_FORMAT = "The command \"%s\" failed.";
    private static final String ERR_CONNECTION = "Could not establish a connection to Tor.";
    private static final String ERR_HALT = "There was a problem shutting Tor down - try again?";
    private static final String ERR_NEWNYM = "Could not switch to a new identity.";
    private static final String ERR_RELOAD = "Could not set the configuration.";
    private static final String ERR_EVENTS = "Could not set the EventHandler.";

    private TorControlConnection connection;

    public JtorctlWrapperImpl(@NonNull Socket torSocket) throws JtorctlException {
        try {
            connection = new TorControlConnection(torSocket);
        } catch (IOException e) {
            throw new JtorctlException(ERR_CONNECTION);
        }
    }

    @Override
    public void signal(String signal) throws JtorctlException {
        try {
            connection.signal(signal);
        } catch (IOException e) {
            throw new JtorctlException(String.format(ERR_SIGNAL_FORMAT, signal));
        }
    }

    @Override
    public void newIdentity() throws JtorctlException {
        try {
            connection.signal(SIGNAL_NEWNYM);
        } catch (IOException e) {
            throw new JtorctlException(ERR_NEWNYM);
        }
    }

    @Override
    public void setConfig(@NonNull Torrc torrc) throws JtorctlException {
        try {
            connection.setConf(torrc.asList());
            connection.saveConf();
            connection.signal(SIGNAL_RELOAD);
        } catch (IOException e) {
            throw new JtorctlException(ERR_RELOAD);
        }
    }

    @Override
    public void stopTor() throws JtorctlException {
        try {
            connection.signal(SIGNAL_HALT);
        } catch (IOException e) {
            throw new JtorctlException(ERR_HALT);
        }
    }

    @Override
    public void setEventHandler(@NonNull EventHandler eventHandler) throws JtorctlException {
        try {
            connection.setEventHandler(eventHandler);
            connection.setEvents(EVENTS);
        } catch (IOException e) {
            throw new JtorctlException(ERR_EVENTS);
        }
    }
}
