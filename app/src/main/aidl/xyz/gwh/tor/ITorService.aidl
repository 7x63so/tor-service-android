package xyz.gwh.tor;
import xyz.gwh.tor.config.Torrc;

interface ITorService {
    void signal(String command);
    void newIdentity();
    void setConfig(in Torrc config);
    void stopTor();
    void exit();
}