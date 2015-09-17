package xyz.gwh.tor;
import xyz.gwh.tor.config.Torrc;

interface ITorService {
    void setConfig(in Torrc config);

    void exit();
}