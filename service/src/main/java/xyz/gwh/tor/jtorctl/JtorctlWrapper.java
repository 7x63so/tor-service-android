package xyz.gwh.tor.jtorctl;

import android.support.annotation.NonNull;

import net.freehaven.tor.control.EventHandler;

import xyz.gwh.tor.config.Torrc;

/**
 * A wrapper for jtorctl - sends command signals to Tor.
 */
public interface JtorctlWrapper {
    /**
     * Attempts to send a signal String to Tor.
     *
     * @throws JtorctlException
     */
    void signal(String command) throws JtorctlException;

    /**
     * Attempts to send a 'NEWNYM' signal to Tor.
     *
     * @throws JtorctlException
     */
    void newIdentity() throws JtorctlException;

    /**
     * Attempts to save the Torrc to disk and send a 'RELOAD' signal to Tor.
     *
     * @throws JtorctlException
     */
    void setConfig(@NonNull Torrc torrc) throws JtorctlException;

    /**
     * Attempts to send a 'HALT' signal to Tor.
     *
     * @throws JtorctlException
     */
    void stopTor() throws JtorctlException;

    /**
     * Sets an EventHandler for Tor events.
     */
    void setEventHandler(@NonNull EventHandler eventHandler) throws JtorctlException;
}
